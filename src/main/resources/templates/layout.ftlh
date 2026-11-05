
<#macro page title>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${title} | Marvel Character Gallery</title>
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>
    <header>
        <h1>Marvel Character Gallery</h1>
        <nav>
            <a href="/">Home</a>
            <a href="/characters/new">Add Character</a>
            <a href="/about">About</a>
        </nav>
    </header>

    <main>
        <#if message??>
            <div class="success-message">
                <p>${message}</p>
            </div>
        </#if>

        <#nested>
    </main>

    <footer>
        <p>Marvel Character Gallery</p>
    </footer>
</body>
</html>
</#macro>