# Prova de Seleção

## Problema
Senhor Eduardo é proprietário de um canil em Belo Horizonte, ele trabalha com diversas raças, pequenas e grandes. Eduardo gosta que seus cães estejam sempre arrumados, felizes e cheirosos.
No bairro do canil, para realizar o banho nos animais, existem três petshops: Meu Canino Feliz, Vai Rex, e ChowChawgas. Cada um deles cobra preços diferentes para banho em cães pequenos e grandes e o preço pode variar de acordo com o dia da semana.
- **Meu Canino Feliz**: Está distante 2km do canil. Em dias de semana o banho para cães pequenos custa R$ 20,00 e o banho em cães grandes custa R$ 40,00. Durante os finais de semana o preço dos banhos é aumentado em 20%.
- **Vai Rex**: Está localizado na mesma avenida do canil, a 1,7km. O preço do banho para dias úteis em cães pequenos é R$ 15,00 e em cães grandes é R$ 50,00. Durante os finais de semana o preço para cães pequenos é R$20,00 e para os grandes é R$ 55,00.
- **ChowChawgas**: Fica a 800m do canil. O preço do banho é o mesmo em todos os dias da semana. Para cães pequenos custa R$ 30,00 e para cães grandes R$ 45,00.
Apesar de se importar muito com seus cãezinhos, Eduardo quer gastar o mínimo possível. Desenvolva uma solução para encontrar o melhor petshop para levar os cães. O melhor petshop será o que oferecer menores preços, em caso de empate o melhor é o mais próximo do canil.

### Input e Output
- Entrada: <data> <quantidade de cães pequenos> <quantidade cães grandes>
  - Exemplo: ```03/08/2018 3 5```
- Saída: Nome do melhor canil e preço total dos banhos.
  - Exemplo: ```"Meu Canino Feliz" 100.00```

## Premissas e considerações
- Dias de semana e dias úteis são representados pelo seguinte intervalo de dias da semana: [segunda-feira, sexta-feira];
- Os pet shops não possuem limite de lavagens diárias;
- O Senhor Eduardo, dono do canil, irá a apenas a um pet shop na data selecionada, portanto, não poderá haver soluções que combinem múltiplos petshops
  > "O melhor petshop será o que oferecer menorespreços, em caso de empate o melhor é o mais próximo do canil."

# Como executar?
