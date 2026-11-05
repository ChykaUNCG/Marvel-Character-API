<#import "layout.ftlh" as layout>

<@layout.page title="Marvel Character Gallery">

    <section class="intro">
        <h2>Explore the Marvel Gallery</h2>

        <p>
            Explore a collection of heroes from the Marvel universe.
        </p>

        <form action="/" method="get">

            <input
                type="text"
                name="name"
                placeholder="Search by character name..."
                value="${name!''}"
            >

            <button type="submit">Search</button>

            <a href="/">Clear</a>

        </form>

        <div class="filter-buttons">
            <a href="/">All Characters</a>
            <a href="/?universe=Marvel">Marvel</a>
        </div>

        <p>Showing ${characters?size} characters</p>
    </section>

    <section class="gallery">

        <#list characters as c>

            <#assign imageName = c.name?lower_case?replace(" ", "")?replace("-", "")>

            <#if imageName == "spiderman">
                <#assign defaultImage = "/images/Spiderman.jpg">
            <#elseif imageName == "ironman">
                <#assign defaultImage = "/images/Iron Man.jpg">
            <#elseif imageName == "captainamerica">
                <#assign defaultImage = "/images/CaptainAmerica.jpg">
            <#elseif imageName == "blackpanther">
                <#assign defaultImage = "/images/BlackPanther.jpg">
            <#elseif imageName == "thor">
                <#assign defaultImage = "/images/Thor.jpg">
            <#else>
                <#assign defaultImage = "/images/Marvel.jpg">
            </#if>

            <#if c.imageUrl?? && c.imageUrl?has_content>
                <#assign characterImage = c.imageUrl>
            <#else>
                <#assign characterImage = defaultImage>
            </#if>

            <div class="character-card">

                <img
                    src="${characterImage}"
                    alt="${c.name}"
                    onerror="this.onerror=null;this.src='/images/Marvel.jpg';"
                >

                <h3>${c.name}</h3>

                <p>
                    <strong>Universe:</strong>
                    ${c.universe!''}
                </p>

                <p class="role">${c.role!''}</p>

                <p>${c.description!''}</p>

                <a href="/characters/${c.characterId?c}">
                    View Details
                </a>

            </div>

        <#else>

            <p>No characters found.</p>

        </#list>

    </section>

</@layout.page>