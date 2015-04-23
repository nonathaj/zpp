package zpp

class ZppTagLib {
    static defaultEncodeAs = [taglib:'html']
    //static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]

	class MyAppTagLib {
		def redirectMainPage = {
			response.sendRedirect("/zpp/home")
		}
	}
}
