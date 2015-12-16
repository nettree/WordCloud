package com.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.htmlparser.beans.StringBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

@SpringBootApplication
@RestController
public class WordcloudApplication {

	/**
	 * main method to generate word cloud, will get URL value from UI and then retrieve
	 * the web page content of given URL, extract pure text content of HTML page, then
	 * count their weight, after return the JSON object to UI 
	 */
	@RequestMapping("/wordcloud")
	public Map<String, Object> generateWordCloud(@RequestHeader(value="url") String url) {
		
		// if URL is null or empty, return set default value to it
		if(url == null || url.isEmpty()) {
		   url = DEFAULT_URL;
		}
		
		// define the htmlparser
		StringBean sb = new StringBean();
		// do not need to acquire the link in the page
		sb.setLinks(false);
		// replace the multiple spaces using single space
		sb.setReplaceNonBreakingSpaces(true);
		// replace the sequence spaces using single space
		sb.setCollapse(true);
		// the url needed to parse
		sb.setURL(url);
		
		// generate word cloud weight map, the key of map is word value, while the value of map is weight of word
		Map<String, Integer> wordMap = new HashMap<String, Integer>();
		String eliminateWords = "i|me|my|myself|we|us|our|ours|ourselves|you|your|yours|yourself|yourselves|he|him|his|himself|she|her|hers|herself|it|its|itself|they|them|their|theirs|themselves|what|which|who|whom|whose|this|that|these|those|am|is|are|was|were|be|been|being|have|has|had|having|do|does|did|doing|will|would|should|can|could|ought|i'm|you're|he's|she's|it's|we're|they're|i've|you've|we've|they've|i'd|you'd|he'd|she'd|we'd|they'd|i'll|you'll|he'll|she'll|we'll|they'll|isn't|aren't|wasn't|weren't|hasn't|haven't|hadn't|doesn't|don't|didn't|won't|wouldn't|shan't|shouldn't|can't|cannot|couldn't|mustn't|let's|that's|who's|what's|here's|there's|when's|where's|why's|how's|a|an|the|and|but|if|or|because|as|until|while|of|at|by|for|with|about|against|between|into|through|during|before|after|above|below|to|from|up|upon|down|in|out|on|off|over|under|again|further|then|once|here|there|when|where|why|how|all|any|both|each|few|more|most|other|some|such|no|nor|not|only|own|same|so|than|too|very|say|says|said|shall";
		String eliminateSymbol = ".|,|~|!|@|#|$|%|^|&|*|(|)|-|_|+|=|{|}|:|;|'|?|/|\\|>|<|";
		for (String word : sb.getStrings().split(" ")) {
			for (String subWord : word.split("\r\n")) {
				// if this word should be elimated
				if(eliminateWords.contains(subWord) || eliminateSymbol.contains(subWord)) {
					continue;
				}
				// if this word already placed, add its number
				if(wordMap.containsKey(subWord)) {
					int count = wordMap.get(subWord);
					wordMap.put(subWord, ++count);
				} else {
					wordMap.put(subWord, 1);
				}
			}
		}
		
		// generate JSON contains words' weight and texts for UI
		JsonArray wordArray = new JsonArray();
		for(Entry<String, Integer> word : wordMap.entrySet()) {
			JsonObject wordObj = new JsonObject();
			wordObj.addProperty("text", word.getKey());
			wordObj.addProperty("weight", word.getValue());
			wordArray.add(wordObj);
		}
		
		// return the result
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("words", wordArray.toString());
		return model;
	}

	public static void main(String[] args) {
		SpringApplication.run(WordcloudApplication.class, args);
	}
	
	// define default URL used to retrieve content
	private final static String DEFAULT_URL = "https://www.parashift.com.au/parablog/";
}
