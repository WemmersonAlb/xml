#Exercícios de Xpath

##Para pesquisar atributos com xpath usamos a seguinte estrutura:

/bibliography/book[@category ="LP"]

Após a barra começamos com a raiz.

Ao chegar no elemento que possui o atributo, utilizamos
colchetes "[ ]"(chamado de predicado), e então um arroba "@" seguido do nome
que procuramos.

##Podemos pesquisar um elemento ou atributo em especifico

element(name, type)
attribute(name,type)

Name é o nome do elemento e type o seu tipo

##Predicados

Podemos usar predicados para muitas coisas, seguem alguns exemplos:

/bibliography/books[2]       Retorna o segundo livro (o índice começa com 1)

//books/title        Pesquisa todo título que está dentro de um livro em algum lugar do xml

(//book/author)[1]   Cria uma lista com todas as ocorrências e retorna o número da ocorrência especificada

(//book/author[3])[1]  Nesse caso retorna a primeira ocorrência de umn terceiro autor em um livro


#Resolvendo questões

1. //book[author[2]]/title/text()
2. count(//book[author[2]])
3. avg(//book[@category = "SO"]/price)      avg(xpath expression) calcula a média


###Questão 11:
let $xml :=<results>{

for $e in (//entry[number(substring(@died, 1, 4)) lt 1600])
order by number($e/@died)
return <dude>{concat($e/title/string-join(csc, ' '),' (' ,$e/substring(@died, 1, 4), ')')}</dude>

}</results>

return
serialize($xml, map{'indent':'yes', 'method':'xml'})

###Desafio: Retornar o nome dos autores que começam com a letra A

for $a in (//book/author)
return  if ($a/substring($a, 1, 1) eq 'A') then $a/text()

###Desafio: Retornar os preços que são números ímpares

for $p in (//book/price)
return  if ($p/number(substring($p, 1, 3)) mod 2 = 0) then $p/text()
