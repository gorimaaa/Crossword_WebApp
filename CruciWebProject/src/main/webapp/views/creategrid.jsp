<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Création de la grille</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/creategrid.css">
<script src="<%=request.getContextPath()%>/js/creategrid.js"></script>
</head>
<body>
<p id="test">Pour créer la grille, merci de remplir ces champs :</p>
<label for="horizontal-dim">Dimension horizontale:</label>
<select name="h-d" id="dim-horizontal" onchange="updateGridSize()">
  <option value="h-1">1</option>
  <option value="h-2">2</option>
  <option value="h-3">3</option>
  <option value="h-4">4</option>
  <option value="h-5">5</option>
</select>

<label for="vertical-dim">Dimension verticale:</label>
<select name="v-d" id="dim-horizontal" onchange="updateGridSize()">
  <option value="v-1">1</option>
  <option value="v-2">2</option>
  <option value="v-3">3</option>
  <option value="v-4">4</option>
  <option value="v-5">5</option>
</select>

<div class="grid">
	<input type="text" class="cell-1" id="cell-1">
</div>
</body>
</html>