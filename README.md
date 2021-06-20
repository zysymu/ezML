# ezML
A native Java implementation of different Machine Learning algorithms!

This was done for learning purposes and is heavily based off of the [Stanford's CS229 course](http://cs229.stanford.edu/): both the 2018 lectures available on YouTube and the lecture notes. The idea behind this project was being able to understand the inner workings of Machine Learning algorithms that are usually treated as "black boxes". A CSV reader and a pre-processing class that performs train-test splits and normalizes the data were also made from scratch :)

## Using ezML
An example of how to use the program can be seen in the [Tests](https://github.com/zysymu/ezML/blob/main/src/app/Test.java) file (`ezML/src/app/Test.java`). The interface with the algorithms is heavily based off of scikit-learn, and is as follows:
1. Load CSV data.
2. Pre-process data, splitting between train and test sets, separating the features from the labels and (optionally) normalizing the data.
3. Choose a classifier and instantiate it setting its hyperparameters.
4. Train the classifier with the train set data.
5. Optionally print the training metrics and the classifier parameters.
6. Test classifier on the test set data.
7. Use the classifier to evaluate on a new data point. (It's necessary to normalize this data point if the training data was also normalized).

## Future
I plan to keep updating **ezML** as I keep watching new CS229 lecture videos, but it may take a while for me to do this :v
