<?php

define('DB_HOSTNAME','localhost');
define('DB_PORT','3306');
define('DB_USER', 'root');
define('DB_PASSWORD','');
define('DB_NAME', 'dwes02');

define('DB_DSN','mysql:host='.DB_HOSTNAME.';port='.DB_PORT.';dbname='.DB_NAME);

echo DB_DSN;