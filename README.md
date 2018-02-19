# AndroidLayoutSet

This is an Android layout simulator

a live graphical simulation of a simple layout manager for a set of simulated linear container widgets and simulated buttons.

XWidget: the parent class for both the simulated container widget and the simulated button. It can be an abstract class.

XFrame: the container class; it has a setting for either HORIZONTAL or VERTICAL layout, similar to a LinearLayout. XFrames
maintain a list of their children (which can be XFrames or XButtons). All XFrames are drawn as yellow rectangles.
XButton: the simulated button class, drawn as a basic blue rectangle with a text label. XButtons have no children.

XFrames always take up all of the available space of their parent, in both dimensions
XFrames always divide their space up equally among their children (which can be XFrames or XButtons)
XButtons take up their preferred width and height if it is available.
If there is not enough space, they shrink to take what is available
If there is more space available than the preferred size, an XButton stays at its preferred size and aligns to the center of the
space allocated to it
The interface should work appropriately whether the device is in Portrait or Landscape mode

