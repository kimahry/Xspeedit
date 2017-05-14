defmodule Xspeedit do
  @moduledoc """
  Documentation for Xspeedit.
  """

  require Logger
  alias Xspeedit.Utils

  @colis_size 10

  def main(args \\ []) do  
    {args, is_numeric} = Utils.parse_args args
    process args, is_numeric
  end

  def process(_, false) do 
    Logger.error "No argument was given or it's invalid"
  end 

  def process(args, true) do
  completed_boxes = []
    args
    |> Utils.parse_colis_to_map
    |> pack(completed_boxes)
    |> Utils.print_completex_boxes
  end

  @doc """
  Return the completed boxes
  """
  @spec pack(:map, [[:Integer]]) :: [:Integer]
  def pack(inventory, completed_boxes) when map_size(inventory) == 0 do
    completed_boxes
  end

  @doc """
  Pack the packages inside a box which have the size of `@colis_size`
  """
  @spec pack(:map, [[:Integer]]) :: [:Integer]
  def pack(inventory, completed_boxes) when map_size(inventory) > 0 do
   {inventory, box} = pick @colis_size-1, inventory, []
   # We add the box to our list of completed boxes 
   completed_boxes = completed_boxes ++ [box]
   # Recursion on pack until inventory is empty
   pack inventory, completed_boxes
  end

  @doc """
  Choose a package to fill the space left in the current box
  Return the package
  """
  @spec pick(:Integer, :map, [:Integer]) :: {:map, [Integer]}
  def pick(max_size, inventory, box) do
    case _pick max_size, inventory do

     {:ok, package, inventory} -> 
       # we add the package to the box
       box = box ++ package      
       # Recursion on pick with the space left in the box
       (@colis_size - Enum.sum box)
        |> pick(inventory, box)

     {:completed, inventory} -> 
       # End recursion
       {inventory, box}

     {0, inventory} ->
      # If no package that match the space left in the box is found in the inventory, the box is completed
      # End recursion
      {inventory, box}

    end
  end

  def _pick(max_size, inventory) when max_size <= 0 do
    # Box is completed
    {:completed, inventory}
  end

  def _pick(max_size, inventory) when max_size > 0 do
    Enum.reduce_while max_size..1, {0, inventory}, fn(package, acc) -> 
      case update_inventory(elem(acc, 1), package) do
        # No key present in the inventory
        {nil, _} -> {:cont, acc}
        # Key found
        {_, inventory} ->   
          {:halt, {:ok, [package], inventory}}
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
