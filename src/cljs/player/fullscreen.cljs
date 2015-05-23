(ns player.fullscreen)

(defn document-prop [name]
  (aget js/document name))

(defn is-fullscreen? []
  (let [options ["fullscreenElement"
                 "mozFullScreenElement"
                 "webkitFullscreenElement"
                 "msFullscreenElement"]]
  (some (comp boolean document-prop) options)))

(defn request [dom-node]
  (let [options ["requestFullscreen"
                 "webkitRequestFullscreen"
                 "mozRequestFullScreen"
                 "msRequestFullscreen"]
        f (some (partial aget dom-node) options)]
    (if f
      (.call f dom-node))))

(defn exit []
  (let [options ["exitFullscreen"
                 "webkitExitFullscreen"
                 "mozCancelFullScreen"
                 "msExitFullscreen"]
        f (some document-prop options)]
    (if f
      (.call f js/document))))

(defn toggle [dom-node]
  (if (is-fullscreen?)
    (exit)
    (request dom-node)))
