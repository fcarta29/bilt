<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed"
                data-toggle="collapse" data-target="#navbar" aria-expanded="false"
                aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span> <span
                    class="icon-bar"></span> <span class="icon-bar"></span> <span
                    class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/">BILT</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li class="${menu.menuItem == 'home' ? 'active' : ''}"><a href="/home">Home</a></li>
                <li class="${menu.menuItem == 'gameweek' ? 'active' : ''}"><a href="/gameweek">Game Week</a></li>
                <li class="${menu.menuItem == 'about' ? 'active' : ''}"><a href="/about">About</a></li>
                <li class="${menu.menuItem == 'contact' ? 'active' : ''}"><a href="/contact">Contact</a></li>
                <li class="dropdown"><a href="#" class="dropdown-toggle"
                    data-toggle="dropdown" role="button" aria-haspopup="true"
                    aria-expanded="false">Settings <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="#">Action</a></li>
                        <li><a href="#">Another action</a></li>
                        <li><a href="#">Something else here</a></li>
                        <li role="separator" class="divider"></li>
                        <li class="dropdown-header">Nav header</li>
                        <li><a href="#">Separated link</a></li>
                        <li><a href="#">One more separated link</a></li>
                    </ul>
                </li>
            </ul>
        </div>
        <!--/.nav-collapse -->
    </div>
</nav>
