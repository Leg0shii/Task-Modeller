# Bachelor-TaskModeller

Ziel der Bachelorarbeit ist es, ein prototypisches Werkzeug zu entwickeln, welches die parallele Erstellung von Ist- und Soll-Aufgabenmodellen ermöglicht. Das
Werkzeug kann sich auf einen bekannten Spezifikationsformalismus für Aufgaben (z.B. CTT) und Aufgabenobjekte stützen, diesen aber auch adaptieren.

### TODO
- [ ] refactor selection stuff if necessary
#### Important
- [x] implement CTT symbols as a toolbar
- [x] right click symbol for changing it
  - name
  - color
  - size
  - connection
- [x] implement selection into the hotbar
- [x] implement CTT properties into the hotbar
- [x] select symbols
- [x] copy and paste of part trees and trees
- [x] possibly descriptions to tasks
- [x] add annotation/text over the canvas
- [x] allow connections be made between two drawing areas
- [x] generalise models as described in paper
- [ ] add a todo/guidelines you can tick (toggle in menu)
- [ ] other edit display for CTT (color)
- [ ] other sizes for modelling
- [ ] right click model to open editor window

- [ ] save and load data and ctte trees (into squares)

#### Enhancements
- [x] drawing area positioning in the middle
- [x] fix resizing of zoom
- [x] zoom in/out with hotkeys
- [ ] centering the screens
- [x] fix screen resize
- [ ] center on one model
- [ ] colorize different models
- [ ] indicate connection process
- [ ] add padding around the models

- [ ] showcase with colors model-changes (toggle in menu)
- [ ] layers
- [ ] max size for drawing boards (because of moving around objects)
- [ ] delete sub tree when deleting for CTT (maybe?)
- [ ] change position of multiple elements at once when selected
- [ ] allow copy from model to model (different (set constraints))

#### Bugs
- [ ] don't switch toolbar when on misc/conn and selecting window
- [ ] check entered values on creation
- [ ] only comments when project is created
- [ ] init project only when init steps are done
- [x] make nodes be selected for each drawing field
- [x] remove selection when adding new item to field
- [x] uncolor nodes after switching window
- [x] select window when item is selected
- [ ] new symbol added is not connected with the selected one but the last

#### Thesis
- [x] Prototypisches Werkzeug Einleitung anpassen
- [ ] Verbindungen fertig schreiben, sobald implementiert