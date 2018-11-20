package com.stock.dbservice.resource;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stock.dbservice.model.Quote;
import com.stock.dbservice.model.Quotes;
import com.stock.dbservice.repository.QuotesRepository;

@RestController
@RequestMapping("/rest/db")
public class DbServiceResource {

	@Autowired
	private QuotesRepository quotesRepository;

	@GetMapping("/{username}")
	public List<String> getQuotes(@PathVariable final String username) {
		return getQuoteByUserName(username);
	}

	@PostMapping("/addQuote")
	public List<String> addQuotes(@RequestBody final Quotes quotes) {
		//quotes.getQuotes().stream().map(quote -> new Quote(quotes.getUsername(), quote)).forEach(quote -> quotesRepository.save(quote));
		
		for(String quote : quotes.getQuotes()) {
			Quote quoteNew = new Quote(quotes.getUsername(), quote); 
			quotesRepository.save(quoteNew);
		}
		
		return getQuoteByUserName(quotes.getUsername());
	}
	
	@PostMapping("/deleteQuote/{username}")
	public List<Quote> deleteQuotes(@PathVariable final String username) {
		List<Quote> quotes = quotesRepository.findByUserName(username);
		quotes.stream().forEach(quote -> quotesRepository.delete(quote));
		return quotesRepository.findByUserName(username);
	}

	public List<String> getQuoteByUserName(final String username) {
		return quotesRepository.findByUserName(username).stream().map(Quote::getQuote).collect(Collectors.toList());
	}
}
