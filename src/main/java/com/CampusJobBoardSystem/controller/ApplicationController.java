<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Applications</title>
    <link rel="stylesheet" th:href="@{/css/styles.css}">
</head>
<body>
<h1>All Applications</h1>

<a th:href="@{/jobs}">Back to Jobs</a>

<table border="1">
    <thead>
        <tr>
            <th>ID</th>
            <th>Student</th>
            <th>Job</th>
            <th>Status</th>
            <th>Actions</th>
        </tr>
    </thead>
    <tbody>
        <tr th:each="app : ${applications}">
            <td th:text="${app.id}">1</td>
            <td th:text="${app.user.fullName}">Student Name</td>
            <td th:text="${app.job.title}">Job Title</td>
            <td th:text="${app.status}">SUBMITTED</td>
            <td>
                <a th:href="@{|/applications/${app.id}|}">View</a>
                <form th:action="@{|/applications/${app.id}/delete|}" method="post" style="display:inline">
                    <button type="submit">Delete</button>
                </form>
            </td>
        </tr>
    </tbody>
</table>
</body>
</html>
