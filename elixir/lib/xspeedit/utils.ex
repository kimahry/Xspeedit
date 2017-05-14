defmodule Xspeedit.Utils do
  @moduledoc """
  Utils class
  """

  require  Logger

  @doc """
  Take a string representing a list of colis and split it into a map that count the number of colis by their size
  Return a `Map` that contain for each size the number of colis
  """
  @spec parse_colis_to_map(:String) :: %{optional(Integer) => Integer}
  def parse_colis_to_map(string_colis) do
    Logger.info "Processing colis:#{string_colis}"
    string_colis
      |> String.split(~r{[0-9]}, include_captures: true, trim: true)
      |> Enum.map(&String.to_integer/1)
      |> Enum.reduce(%{}, fn(colis, acc) -> Map.update(acc, colis, 1, &(&1 + 1)) end)
      |> Map.delete(0)
  end

  @doc """
  Print the packages seperated by a /
  """
  @spec print_completex_boxes(:String) :: :none
  def print_completex_boxes (completed_boxes) do
    Logger.info "Number of box: #{length completed_boxes}"
    r = Enum.reduce(completed_boxes, "", fn(x,acc) ->  acc <> (Enum.join x) <> "/" end)
    |> String.replace_trailing("/", "")
    Logger.info "Packages: #{r}"
  end
  
  @doc """
  Parse the program argument
  Return a tuple with the argument and whetever or not it contain only numerical value
  """
  @spec parse_args(:any) :: {:any, :boolean}
  def parse_args(args) do
    {opts, _, _} = OptionParser.parse(args, switches: [colis: :string], aliases: [c: :colis])
    case Keyword.get(opts, :colis, []) do
      [] -> {[], false}
      arg -> {arg, Regex.match?(~r/^[0-9]*$/, arg)}
    end
    
  end

end