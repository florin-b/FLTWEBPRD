

<%@ taglib prefix="menuoptions" uri="/WEB-INF/tlds/navigator.tld"%>
<aside id="sidebar" class="column-left">
	<ul>
		<li>
			<h4>Meniu</h4>
			<ul class="blocklist">
				<menuoptions:navigator>
					<li><a href="${navdetails.link}">${navdetails.text}</a></li>
				</menuoptions:navigator>
			</ul>
		</li>


	</ul>
</aside>

