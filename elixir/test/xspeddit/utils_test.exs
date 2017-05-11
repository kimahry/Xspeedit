defmodule Xspeedit.UtilsTest do
  use ExUnit.Case
  doctest Xspeedit.Utils

  alias Xspeedit.Utils

  test "parsing colis to map" do
    inventory = Utils.parse_colis_to_map("15985122594517712333125478")
    assert is_map inventory
    assert is_integer inventory[1]
  end

end
