## Styling
Requires the following NPM packages for styling (not an exhaustive list):
- @vue/cli-plugin-babel
- @vue/cli-plugin-eslint
- @vue/eslint-config-prettier
- babel-eslint
- eslint
- eslint-plugin-vue

In `package.json`, under `eslintConfig`:

```
"eslintConfig": {
  "root": true,
  "env": {
    "node": true
  },
  "extends": [
    "plugin:vue/essential",
    "eslint:recommended"
  ],
  "parserOptions": {
    "parser": "babel-eslint"
  },
  "rules": {}
}
```


## Proxy config
If a local dev server is required, you can proxy requests via `vue.config.js`

## Cool Packages
- Best drag & drop component system: `vuedraggable`
- UUIDs: `uuid`