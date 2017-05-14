defmodule XspeeditTest do
  use ExUnit.Case
  doctest Xspeedit

  require Logger

  test "pick package" do
    inventory = %{9 => 1, 8 => 2, 7 => 3, 1 => 1}
    {:ok, package, _} = Xspeedit._pick 10, inventory
    assert package == [9]  
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

  test "case 1" do
    Logger.info "Case 1"
    Xspeedit.main(["--colis", "163841689525773"]);
  end

  test "case 2" do
    Logger.info "Case 2"
    Xspeedit.main(["--colis", "163841689525773"]);
  end

  test "case 3" do
    Logger.info "Case 3"
    Xspeedit.main(["--colis", "442211111"]);
  end

  test "case 4" do
    Logger.info "Case 4" 
    Xspeedit.main(["--colis", "1638112349078765557678768979023848238423843241689525773"]);
  end

  test "case 5" do
    Logger.info "Case 5"
    Xspeedit.main(["--colis", "32111111"]);
  end

  test "case 6" do
    Logger.info "Case 6"  
    Xspeedit.main(["--colis", "16381123490787189489218791451121564748712121212154778321548792123265987452121111111112222222255555544778999962222445454878111983065557678768979023848238423843241689525773"]);
  end

  test "case 7" do
    Logger.info "Case 7"
    Xspeedit.main(["--colis", "32pl111111"]);
  end

  test "case 8" do
    Logger.info "Case 8"
    Xspeedit.main(["--colis", "32l+111111"]);
  end

end
