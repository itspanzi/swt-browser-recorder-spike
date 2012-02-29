## Description

This is a spike to show that it is very easy to write a **Web Recorder** using the SWT Browser Component.You can checkout the documentation for the browser component [here](http://www.eclipse.org/swt/snippets). Look at the snippets for "Browser".

SWT Browser component uses a native rendering enging inside SWT. For example, it uses XUL Runner to render Mozilla etc. This way, even though you do not get the perceived safety of running in a real browser, the functionality is still exactly the same.

## Related

[Krypton](https://github.com/thoughtworks/krypton) is a SWT Browser based Web testing driver that can drive a browser just the way Webdriver, Sahi etc can. Its a dormant project though and not much active code has been written since begining 2011.

## Implementation

The way the recorder is implemented is by leveraging the concept of a [BrowserFunction](http://help.eclipse.org/galileo/index.jsp?topic=/org.eclipse.platform.doc.isv/reference/api/org/eclipse/swt/browser/BrowserFunction.html). This is the key to the way the whole spike works. 

A BrowserFunction is an abstraction over a Java method that can be invoked from Javascript as if it defined in JS. This is quite amazing and is very powerful. 

The way the spike uses this as follows:

* After a page is loaded, use something like JQuery and register all event handlers that you are interested on all components
** The spike registers 'click' handlers for all links
* The event handler itself is a SWT BrowserFunction that gets invoked with the right set of arguments
** The spike uses a simple function that prints out different attributes of the link that was clicked upon
* Using this information, get the most appropriate locator and record that

This way, one can very easily implement a recorder

## Do not reinvent the recorder

Instead of reinventing the Javascript side of things, one can potentially use something like the JS Recorder from [SAHI](http://sahi.co.in/w/) and use it to register the right events and the handlers for these events can be the BrowserFunctions.

This way, we will be relying on another open source project and not trying to solve the problem of a JS based recorder from scratch.
