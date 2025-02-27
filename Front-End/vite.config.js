/* eslint-disable no-undef */
import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'
import path from 'path'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [react()],
  resolve: {
    alias: {
      '@component': path.resolve(__dirname, 'src/component'),
      '@pages': path.resolve(__dirname, 'src/pages'),
      '@globalstate': path.resolve(__dirname, 'src/globalstate'),
    },
  },
})
