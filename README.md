# elv and file storage

Elv is a Clojure library designed to help with logging and viewing exceptions on the ring server.

Efv.file-storage stores elv logs to file system

## Usage

## Geting started

Elv.file-storage artifacts are [deployed to clojars] (https://clojars.org/elv.file-storage) 

With Leiningen:

    [elv.file-storage "0.1.1"]

With Gradle:
    compile "elv.file-storage:elv.file-storage:0.1.1"

With Maven:

    <dependency>
      <groupId>elv.file-storage</groupId>
      <artifactId>elv.file-storage</artifactId>
      <version>0.1.1</version>
    </dependency>
    

##Usage

``` clojure
(require '[elv.core :refer :all])

(require '[elv.file-storage :refer :all])

(def app
  (-> your-handler (wrap-exception :storage (->LocalFileSystemStorage "some file system path"))))
```

Instead of *some file system path* we provide path to directory where elv will store logs.

## License

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
