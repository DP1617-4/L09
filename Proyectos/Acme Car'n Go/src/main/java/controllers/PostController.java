
package controllers;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.PostService;
import domain.Post;
import forms.FilterString;

@Controller
@RequestMapping("/post")
public class PostController extends AbstractController {

	public PostController() {
		super();
	}


	@Autowired
	private PostService	postService;


	@RequestMapping(value = "/listRequests", method = RequestMethod.GET)
	public ModelAndView listRequests() {
		ModelAndView result;

		Collection<Post> requests;
		final FilterString filter = new FilterString();

		requests = this.postService.findAllRequests();

		result = new ModelAndView("post/list");
		result.addObject("requestURI", "post/customer/listRequests.do");
		result.addObject("posts", requests);
		result.addObject("filterString", filter);

		return result;
	}

	@RequestMapping(value = "/listOffers", method = RequestMethod.GET)
	public ModelAndView listOfferss() {
		ModelAndView result;

		Collection<Post> offers;
		final FilterString filter = new FilterString();

		offers = this.postService.findAllOffers();

		result = new ModelAndView("post/list");
		result.addObject("requestURI", "post/customer/listOffers.do");
		result.addObject("posts", offers);
		result.addObject("filterString", filter);

		return result;
	}

	@RequestMapping(value = "/filter", method = RequestMethod.POST, params = "filterButton")
	public ModelAndView filter(@Valid final FilterString filterString, final BindingResult binding) {

		ModelAndView result;
		Collection<Post> posts;
		final String filter = filterString.getFilter();
		if (binding.hasErrors())
			result = new ModelAndView("redirect:list.do");
		else
			try {
				posts = this.postService.findAllFiltered(filter);
				result = new ModelAndView("post/list");
				result.addObject("requestURI", "post/list.do");
				result.addObject("posts", posts);
				result.addObject("filterString", filterString);

			} catch (final Throwable oops) {
				result = new ModelAndView("redirect:list.do");
			}

		return result;
	}
}
