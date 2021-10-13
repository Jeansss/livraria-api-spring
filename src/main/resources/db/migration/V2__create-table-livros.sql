CREATE TABLE "livros" (
  "id" BIGSERIAL PRIMARY KEY NOT NULL,
  "titulo" varchar NOT NULL,
  "data" date NOT NULL,
  "paginas" int NOT NULL,
  "autor_id" BIGSERIAL NOT NULL
);