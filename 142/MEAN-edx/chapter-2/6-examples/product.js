var mongoose = require('mongoose');
var Category = require('./category');


// schema is also called model in java
var productSchema = {
  name: { type: String, required: true },
  // Pictures must start with "http://"
  pictures: [{ type: String, match: /^http:\/\//i }],
  price: {
    amount: { type: Number, required: true },
    // Only 3 supported currencies for now
    currency: {
      type: String,
      enum: ['USD', 'EUR', 'GBP'],
      required: true
    }
  },
  
  // relational database
  // module.exports.categorySchema = categorySchema;
  // mongoose does not support embedded schema -> therefore Plain Javascript Object
  // i.e. Category.categorySchema instead of Category
  category: Category.categorySchema // Plain Old Javascript Object, rather than embedded schema
};

module.exports = new mongoose.Schema(productSchema);
module.exports.productSchema = productSchema;