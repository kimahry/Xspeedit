defmodule Xspeedit.Utils do
  @moduledoc """
  Utils class
  """

@doc """
Take a string representing a list of colis and split it into a map that count the number of colis by their size
Return a `Map` that contain for each size the number of colis
"""
  # @spec parse_colis_to_map(::String)
  def parse_colis_to_map(string_colis) do
    string_colis
      |> String.split( ~r{[0-9]}, include_captures: true, trim: true)
      |> Enum.map(&String.to_integer/1)
      |> Enum.reduce(%{}, fn(colis, acc) -> Map.update(acc, colis, 1, &(&1 + 1)) end)
  end

end