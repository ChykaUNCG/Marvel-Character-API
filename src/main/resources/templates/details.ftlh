<#import "layout.ftlh" as layout>

<@layout.page title="${character.name} Details">

    <a class="back-link" href="/">
        ← Back to Gallery
    </a>

    <section class="details-card">

        <div class="details-content">

            <#assign imageName = character.name?lower_case?replace(" ", "")?replace("-", "")>

            <#if imageName == "spiderman">
                <#assign characterImage = "/images/Spiderman.jpg">
            <#elseif imageName == "ironman">
                <#assign characterImage = "/images/Iron Man.jpg">
            <#elseif imageName == "captainamerica">
                <#assign characterImage = "/images/CaptainAmerica.jpg">
            <#elseif imageName == "blackpanther">
                <#assign characterImage = "/images/BlackPanther.jpg">
            <#elseif imageName == "thor">
                <#assign characterImage = "/images/Thor.jpg">
            <#else>
                <#assign characterImage = "/images/Marvel.jpg">
            </#if>

            <img
                src="${characterImage}"
                alt="${character.name}"
                style="width: 100%; max-width: 350px; height: auto; display: block; margin: 0 auto 20px;"
            >

            <p class="section-label">
                Quick Info
            </p>

            <h2>${character.name}</h2>

            <p class="character-summary">
                ${character.description!''}
            </p>

            <div class="quick-info">

                <p>
                    <strong>Universe:</strong>
                    ${character.universe!''}
                </p>

                <p>
                    <strong>Role:</strong>
                    ${character.role!''}
                </p>

            </div>

            <h3>Backstory</h3>

            <p>
                ${character.description!''}
            </p>

            <div class="character-actions">

                <a href="/characters/${character.characterId?c}/edit">
                    Edit Character
                </a>

                <form
                    action="/characters/${character.characterId?c}/delete"
                    method="post"
                    onsubmit="return confirm('Are you sure you want to delete this character?');"
                >
                    <button type="submit">
                        Delete Character
                    </button>
                </form>

            </div>

        </div>

    </section>

    <a class="back-link" href="/">
        View All Characters
    </a>

</@layout.page>