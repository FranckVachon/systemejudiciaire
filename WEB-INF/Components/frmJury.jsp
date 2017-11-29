<style>
label {
	width: 100px;
	display: inline-block;
}

input, div.select-wrap {
	display: inline-block;
	width: 200px;
}
</style>

<form action="Jurys" method="get">
	<div class="panel">
		<div class="row">
			<div class="col-xs-6">
				<label for="id">ID</label> <input name="id" type="number" />
			</div>
		</div>
		<div class="row">
			<div class="col-xs-6">
				<label for="prenom">Prenom</label> <input name="prenom" type="text" />
			</div>
			<div class="col-xs-6">
				<label for="nom">Nom</label> <input name="nom" type="text" />
			</div>
		</div>
		<div class="row">
			<div class="col-xs-6">
				<label for="sexe">Sexe</label>
				<div class="select-wrap">
					<select name="sexe">
						<option value="F" selected="selected">F</option>
						<option value="M">M</option>
					</select>
				</div>
			</div>
			<div class="col-xs-6">
				<label for="age">Age</label> <input name="age" type="number" />
			</div>
		</div>
		<div class="row">
			<input class="btn_black" type="submit" value="Ajouter">
		</div>
	</div>
</form>
