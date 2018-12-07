# Stripe Top Customers Viewer

# Table of Contents
- [What is Stripe Top Customers Viewer?](#what-is-stripe-top-customers-viewer-)
  * [How to get started](#how-to-get-started)
    + [Step 1: Add your Stripe API key](#step-1--add-your-stripe-api-key)
    + [Step 2: Start your Spring Boot server](#step-2--start-your-spring-boot-server)
    + [Step 3: Navigate to home page](#step-3--navigate-to-home-page)
    + [Step 4: Login into your account](#step-4--login-into-your-account)
    + [Step 5: View your data](#step-5--view-your-data)
      - [Important Note](#important-note-)
- [Future Work](#future-work)

# What is Stripe Top Customers Viewer?
Stripe Top Customers Viewer (aka, CTSV) is an easy way to view your top customers' data outside of Stripe Dashboard. This allows you to see all key information in a tabular form rather than as individual records that you need to view one-by-one.

## How to get started
Currently, CSV is in a development phase, so you will need to prepare your machine for running the application locally.
To get starterd, you will need to have next software pre-installed:
* IDE of choice (ex. [Intellij IDEA])
* [JDK 1.8 or higher]
* [Node.js]
* [Maven]
* [Yarn]

### Step 1: Add your Stripe API key
In order to view for your Stripe Top Customers, your account's API key is required. 
If you are running a development environment, we recommend you user your test API-token.

![figure1](https://github.com/dmitryvinn/stripe-top-customers/blob/master/docs/assets/figure1.png "figure1")
Figure 1: Retrieving Test Secret Key from Stripe Dashboard

After you retrieve your secret key, in *config/application-dev.yml* (if you are working in dev environment), set next value as your Stripe API Key:
`application.stripeapikey.value`


### Step 2: Start your Spring Boot server
After you install all the required software, you will need to start your Spring Boot Server.
You can either do it by running this command in the project directory:
```
mvn  spring-boot:run
``` 

Alternatively, you can start your server in your IDE:

![figure2](https://github.com/dmitryvinn/stripe-top-customers/blob/master/docs/assets/figure2.png "figure2")
Figure 2: Running Spring Boot Server in Intellij IDEA

### Step 3: Navigate to home page
After your server successfully starts, you will see a link to your running server. Usually, your app should be running under 8080 port, but if it is used by other processes, Spring Boot will use another, unoccupied port:

![figure3](https://github.com/dmitryvinn/stripe-top-customers/blob/master/docs/assets/figure3.png "figure3")
Figure 3: Successfully Started Server

### Step 4: Login into your account
We take security seriously! This is why Stripe Top Customers Viewer has a built-in Authentication system. While running in development mode, you should be able to login using pre-defined credentials, but use proper accounts when in production.

![figure4](https://github.com/dmitryvinn/stripe-top-customers/blob/master/docs/assets/figure4.png "figure4")
Figure 4: Home Page of STCV in Dev Mode

![figure5](https://github.com/dmitryvinn/stripe-top-customers/blob/master/docs/assets/figure5.png "figure5")
Figure 5: Authentication Window

### Step 5: View your data
After you login, navigate to the **View Top Customers**.

![figure6](https://github.com/dmitryvinn/stripe-top-customers/blob/master/docs/assets/figure6.png "figure6")
Figure 6: View Top Customers Navigation


where a table of all* your customers will be displayed with: 
* Customer ID
* Customer Email
* Delinquency Status
* Account Origin

![figure7](https://github.com/dmitryvinn/stripe-top-customers/blob/master/docs/assets/figure7.png "figure7")
Figure 7: Example of Top Customers Data  


#### Important Note
While in development mode, only top 50 records of your account are processed. This is due to the main performance bottleneck of this application - retrieval of charges each customers has. 
Currently, to process over 300 customers, combine their individual charges and consequently sort these customers by these charges can take over 1.5 minutes. 

If you would like to enable processing of all customers, in ``com.stripe.customers.list.service.util.StripeApiUtility.java``, uncomment line 33.

# Future Work
Before this Stripe Top Customers Viewer can be released to production, there are many parts of the application that need polishing:
* JHipster Branding needs to be removed, so users can set their own branding
* Allow users to pick and choose what fields they want to display in the tabular form (outside of the previously mentioned ones)
* Fix major performance issue of chargers calculation by requesting a bulk-api from Stripe developers
* Add pagination to the tabular results
* Attend to all ```TODOs``` in code 
* Increase test coverage of the application

[AngularJS]: <http://angularjs.org>
[Spring Boot]: <http://spring.io/projects/spring-boot>
[Spring Boot]: <http://spring.io/projects/spring-boot>
[Intellij IDEA]: <https://www.jetbrains.com/idea/>
[JDK 1.8 or higher]: <https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html>
[Node.js]: <https://nodejs.org/en/>
[Maven]: <https://maven.apache.org/install.html>
[Yarn]: <https://yarnpkg.com/lang/en/docs/install/>
