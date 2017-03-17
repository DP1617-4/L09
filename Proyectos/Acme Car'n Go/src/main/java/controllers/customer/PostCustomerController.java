
package controllers.customer;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CustomerService;
import services.PostService;
import controllers.AbstractController;
import domain.Post;

@Controller
@RequestMapping("/post/customer")
public class PostCustomerController extends AbstractController {

	public PostCustomerController() {
		super();
	}


	@Autowired
	private PostService		postService;

	@Autowired
	private CustomerService	customerService;


	@RequestMapping(value = "/listOwn", method = RequestMethod.GET)
	public ModelAndView listOwn() {
		ModelAndView result;

		Collection<Post> posts;

		posts = this.postService.findAllByCustomer(this.customerService.findByPrincipal());

		result = new ModelAndView("post/list");
		result.addObject("posts", posts);

		return result;
	}

	@RequestMapping(value = "/postOffer", method = RequestMethod.GET)
	public ModelAndView postOffer() {
		ModelAndView result;

		Post offer;

		offer = this.postService.create();
		offer.setType("OFFER");

		result = new ModelAndView("post/edit");
		result.addObject("post", offer);

		return result;
	}

	@RequestMapping(value = "/postRequest", method = RequestMethod.GET)
	public ModelAndView postRequest() {
		final ModelAndView result;

		Post post;

		post = this.postService.create();
		post.setType("REQUEST");

		result = this.createEditModelAndView(post);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int postId) {
		ModelAndView result;

		Post post;

		post = this.postService.findOne(postId);

		result = this.createEditModelAndView(post);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView edit(@Valid Post post, final BindingResult binding) {

		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(post);
		else
			try {
				post = this.postService.reconstruct(post, binding);
				post = this.postService.save(post);
				result = new ModelAndView("redirect:/post/display.do?postId=" + post.getId());

			} catch (final Throwable oops) {
				result = this.createEditModelAndView(post, "post.commit.error");
				result.addObject("post", post);
			}

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(Post post, final BindingResult binding) {
		ModelAndView result;

		try {
			post = this.postService.reconstruct(post, binding);
			this.postService.delete(post);
			result = new ModelAndView("redirect:/post/list.do");
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(post, "post.commit.error");
		}

		return result;
	}

	// Ancillary Methods

	protected ModelAndView createEditModelAndView(final Post post) {
		ModelAndView result;

		result = this.createEditModelAndView(post, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Post post, final String message) {
		ModelAndView result;

		result = new ModelAndView("post/edit");
		result.addObject("post", post);
		result.addObject("message", message);

		return result;
	}

}