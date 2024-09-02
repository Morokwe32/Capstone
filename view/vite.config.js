import { fileURLToPath, URL } from 'node:url'
import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'


//dotenv.config();
export default defineConfig({
  plugins: [vue()],
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url))
    }
  },
  server: {
    port: 5173, // Change this to your preferred port
    // Other server options...
  },
  build: {
    // Other build options...
  },
  assetsInclude: ['**/*.png', '**/*.PNG']
})

/*
export default defineConfig({
plugins: [
vue(),
],
resolve: {
alias: {
'@': fileURLToPath(new URL('./src', import.meta.url))
}
}
})*/