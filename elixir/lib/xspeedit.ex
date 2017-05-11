defmodule Xspeedit do
  @moduledoc """
  Documentation for Xspeedit.
  """

  require Logger
  alias Xspeedit.Utils

  @colis_size 10

  def run do
    colis = "163841689525773"
    completed_boxes = []
    Logger.info "Processins colis:#{colis}" 

    inventory = Utils.parse_colis_to_map colis

  end

  def pack(completed_boxes, inventory) when map_size(inventory) > 0 do
    {box, inventory} = pick @colis_size - 1, inventory
  end

  def pick(max_size, inventory) do
    {box, inventory} = 
    Enum.reduce_while max_size..1, {[], inventory}, fn(package, acc) -> 
    case update_inventory(elem(acc, 1), package) do
      # No key present in the inventory
      {nil, _} -> {:cont, acc}
      {_, inventory} -> 
        # Add to the box
        box = elem(acc, 0) ++ [package]        
        {:halt, {box, inventory}}
      end 
    end
  end

  def update_inventory(inventory, package) do
    Map.get_and_update(inventory, package, fn value -> 
      cond do
        value == nil -> :pop
        value == 1 -> :pop
        value > 1 -> {value, value - 1}
        end
      end)
  end
  
end
