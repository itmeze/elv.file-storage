(ns elv.file-storage
	(:require 	[elv.storage :refer :all]
				[cheshire.core :refer :all]
				[cheshire.generate :refer [add-encoder encode-str remove-encoder]])
	(:import (java.io ByteArrayInputStream)))

(add-encoder ByteArrayInputStream
						 (fn [c jsonGenerator]
							 (.writeString jsonGenerator (slurp c))))
(add-encoder java.lang.Object encode-str)

(defrecord LocalFileSystemStorage [path]
	LogStorage
	(store
		[this error]
		(spit (str path (:id error) ".log") (generate-string error)))
	(retrive
		[this id]
		(parse-string (slurp (str path id ".log")) keyword))
	(search
		[this page size conditions order]
		(if conditions (throw (Exception. "Filtering is currently not supported for local FileSystem Storage")))
		(if order (throw (Exception. "Ordering is currently not supported for local FileSystem Storage")))
		(let [p (or page 1)
					s (or size 25)
					skip (* (- p 1) s)
					dir (java.io.File. path)
					files (sort-by #(.lastModified %) #(compare %2 %1) (.listFiles dir))]
			(map #(-> % .getAbsolutePath slurp (parse-string keyword)) (take s (drop skip files))))))