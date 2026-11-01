# Anclaje (Fabric, Minecraft 26.3)

- **Caña**: con algo enganchado (mob u objeto), al recoger la caña te impulsas hacia
  él; la entidad no se mueve. Después el gancho se suelta (como vanilla).
- **Endermite = ancla**: generado con huevo de aparición o `/summon`, queda estático
  (sin IA, sin gravedad, sin despawn). Al engancharlo, el gancho NO se suelta:
  cada clic derecho te impulsa de nuevo. Con Shift + clic derecho te impulsas y se suelta.
- **Silverfish**: igual de estático (huevo o `/summon`), pero al engancharlo te impulsa
  y el gancho se suelta.
- **Murciélagos**: vuelven a ser normales.
- Naturales (silverfish de bloques infestados, endermite de perlas) no cambian.
- `/summon` con NBT no pasa por la inicialización: agrega `{NoAI:1b,NoGravity:1b}`
  (todo endermite/silverfish con NoAI se comporta como estático).

## Compilar con GitHub
Sube todo (incluida `.github/workflows/build.yml`), ve a **Actions** y descarga el
artefacto **anclaje-jar** al terminar. Release: `git tag v1.0.1 && git push origin v1.0.1`.
