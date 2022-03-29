<?php
echo "Do you want to set \"replace\" to \"true\"? (Y/N)\n";
$in = trim(fgets(STDIN));
$replace = false;
if (strtoupper($in) == "Y") $replace = true;

// https://fabricmc.net/wiki/tutorial:blocks
echo "Type(example: pickaxe): ";
$in = trim(fgets(STDIN));
$type = $in;

echo "values(item id): (Separated by ',')\n";
$in = trim(fgets(STDIN));
$values = '';
foreach (explode(',', $in) as $id) {
	$values .= "    " . '"' . $id . '",' . "\n";
}
$values = substr($values, 0, -2);

file_put_contents("./" . $type . ".json", "{\n  \"replace\": false,\n  \"values\": [\n" . $values . "\n  ]\n}");