# Cloud Service Programming Exercise
## Reverse Geocoding
 
Design and implement a RESTful web service (HTTP) which is capable of looking up a physical street formattedAddress given a set of geographic coordinates (longitude and latitude values). For example, given the latitude '33.969601' and longitude '-84.100033', the service should return the formattedAddress of the NCR office in Duluth, GA (2651 Satellite Blvd, Duluth, GA 30096).  The implementation should delegate to an online geocoding API (i.e., Google Maps or similar) to perform the lookup; the implementation will serve as a basic abstraction to simplify usage of one or more external services that handle the geo-location aspects.

### Functional Requirements:

- The service should provide a resource for accepting latitude and longitude coordinates for a location on Earth.

- The service, for a valid set of coordinates, return the full, street formattedAddress (including city, state/province, and zip/postal code) of the location at those coordinates.

- The service should cache (locally) the last 10 lookups and provide an additional RESTful API for retrieving this stored data.  The data returned from this API should be a collection of the lookups performed, including the longitude and latitude values, the formattedAddress found, and the date/time of the lookup. 

### Non-Functional Requirements:

- The sample project must include instructions for building and running the project.  Preferably, projects should use a build tool such as Maven, Gradle, Make, etc.

- The service should handle any error conditions (such as invalid input or internal errors) with suitable HTTP error responses. 

- The developer is responsible for designing the API signatures, including the input/output data structures, and any exceptions deemed necessary.

- Although Java is preferred, the choice of language and frameworks is at the discretion of the developer.  Ideally, the application will run as a simple process/executable, and not require an external container or web server to run.

- Projects can be submitted to us either via a zip/tarball containing all source, or alternatively, a link to an available GitHub, Bitbucket, or similar repository.
