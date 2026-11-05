<#import "layout.ftlh" as layout>

<@layout.page title=(editing?then("Edit Character", "Add Character"))>

    <section class="form-section">

        <h2>
            <#if editing>
                Edit Character
            <#else>
                Add a New Character
            </#if>
        </h2>

        <p>
            <#if editing>
                Update the character information below.
            <#else>
                Enter the information below to add a fictional
                character to the gallery.
            </#if>
        </p>

        <form
            action="<#if editing>/characters/${character.characterId?c}<#else>/characters</#if>"
            method="post"
        >

            <label for="name">
                Character Name
            </label>

            <input
                type="text"
                id="name"
                name="name"
                value="${character.name!''}"
                placeholder="Enter character name"
                required
            >

            <label for="universe">
                Universe
            </label>

            <input
                type="text"
                id="universe"
                name="universe"
                value="${character.universe!''}"
                placeholder="Enter universe"
            >

            <label for="role">
                Role
            </label>

            <input
                type="text"
                id="role"
                name="role"
                value="${character.role!''}"
                placeholder="Enter character role"
            >

            <label for="imageUrl">
                Image URL
            </label>

            <input
                type="text"
                id="imageUrl"
                name="imageUrl"
                value="${character.imageUrl!''}"
                placeholder="Enter image URL (example: /images/Thor.jpg)"
            >

            <label for="description">
                Short Description
            </label>

            <textarea
                id="description"
                name="description"
                rows="5"
                placeholder="Enter a short description"
                required
            >${character.description!''}</textarea>

            <button type="submit">
                <#if editing>
                    Save Changes
                <#else>
                    Add Character
                </#if>
            </button>

        </form>

        <a class="back-link" href="/">
            ← Back to Gallery
        </a>

    </section>

</@layout.page>