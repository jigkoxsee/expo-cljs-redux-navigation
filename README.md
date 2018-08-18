Kaesad App build by React Native and Expo
===
# Setup
### Require
- yarn (recommend, it faster) or npm
- XDE - [here](https://docs.expo.io/versions/v15.0.0/introduction/installation.html)
- JDK 8+ - [here](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)

### Install
- yarn global add exp eslint
- cd mobile && yarn
- install lein ([here](http://www.lispcast.com/clojure-ubuntu) or [here](https://leiningen.org/#install))  
  - Mac `brew install leiningen`


# Run
1. Open XDE - Sign up if you have to
1. click `Project` > `Open Project`
1. Browser to this project at `mobile`.
1. After it say `Project opened! You can now use the "Send Link" or "Device" buttons to view your project.`
1. Check! your exp should run in LAN mode - by click gear icon on the top left under project button > Host
1. click `Device` then choose your options, your mobile must connect to same Wifi as your computer
1. Run lein REPL with command `rlwrap lein figwheel` or `lein figwheel` if you don't have rlwarp
1. In XDE mobile's log (right side) will show `Figwheel: socket connection established`
1. In lein REPL will show REPL prompt

# Generate document

    lein codox

# Dev Guide

### Navigation

Pass only id and necessary data when navigate. [example here](https://github.com/jigkoxsee/kaesad-app/blob/56b561d260591f0669f0c262baeb300539ae350b/mobile/src/mobile/features/lms/screen.cljs#L110)