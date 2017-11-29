<style>

</style>

<form action="Avocats" method="get">
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
				<label for="age">Age</label> <input name="age" type="number" />
			</div>
			<div class="col-xs-6">
				<label for="type">Type</label>
				<div class="select-wrap">
					<select name="type">
						<option value="0" selected="selected">Défense</option>
						<option value="1">Poursuite</option>
					</select>
				</div>
			</div>
		</div>
		<div class="row">
			<input class="btn_black" type="submit" value="Ajouter">
		</div>
	</div>
</form>
