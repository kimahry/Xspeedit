defmodule XspeeditTest do
  use ExUnit.Case
  doctest Xspeedit

  test "pick package" do
    inventory = %{1 => 1, 8 => 2}
    acc = Xspeedit.pick 1, inventory
    IO.inspect acc
  end

  test "update inventory with existing key and number > 1" do
    inventory = %{1 => 1, 8 => 2}
    {_curval, newval} = Xspeedit.update_inventory inventory, 8
    assert is_map newval
    assert newval[8] == 1
  end

  test "update inventory with existing key and number == 1" do
    inventory = %{1 => 1, 8 => 2}
    {_curval, newval} = Xspeedit.update_inventory inventory, 1
    assert is_map newval
    assert is_nil newval[1]
  end

  test "update inventory with non existing key" do
    inventory = %{1 => 1, 8 => 2}
    {curval, _newval} = Xspeedit.update_inventory inventory, 9
    assert is_nil curval
  end

end
