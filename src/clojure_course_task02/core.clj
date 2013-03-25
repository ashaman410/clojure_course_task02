(ns clojure-course-task02.core
  (:import java.io.File)
  (:gen-class))


(defn find-files [pattern directory]
  (->> (file-seq (File. directory))
       (pmap #(let [file-name (. % getName)]
               (when-not (nil? (re-find (re-pattern pattern) file-name)) file-name)))
       (remove nil?)))

(defn usage []
  (println "Usage: $ run.sh file_name path"))

(defn -main [file-name path]
  (if (or (nil? file-name)
          (nil? path))
    (usage)
    (do
      (println "Searching for " file-name " in " path "...")
      (dorun (map println (find-files file-name path))))))
