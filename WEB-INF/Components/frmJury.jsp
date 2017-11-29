<style>
</style>

<form action="/action_page.php">
	<div>
		<div class="row">
			<label for="id">ID</label> <input name="id" type="text" />
		</div>
		<div class="row">
			<label for="prenom">Prenom</label> <input name="prenom" type="text" />
			<label for="nom">Nom</label> <input name="nom" type="text" /> 
			<input type="submit" value="submit">
		</div>
		<div class="row">
			<label for="sexe">Sexe</label>
			<select>
				<option value="0">F</option>
				<option value="1">M</option>				
			</select>
			<label for="age">Age</label> <input name="age" type="number" /> 
		</div>
		<input type="submit" value="submit">
	</div>
</form>
