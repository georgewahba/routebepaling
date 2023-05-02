<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>

    <style>
        table {
  border-collapse: collapse;
  width: 100%;
  max-width: 800px;
  margin: 0 auto;
  font-size: 1rem;
  line-height: 1.5;
  font-weight: 400;
  color: #333;
  background-color: #fff;
  border-radius: 0.25rem;
  overflow: hidden;
  box-shadow: 0 0 1rem 0 rgba(0, 0, 0, 0.1);
}

th,
td {
  padding: 1rem;
  text-align: left;
}

th {
  background-color: #f5f5f5;
  font-weight: 600;
  text-transform: uppercase;
  letter-spacing: 0.1em;
}

tbody tr:nth-child(even) {
  background-color: #f5f5f5;
}

tfoot td {
  font-weight: 600;
}

tfoot td:first-of-type {
  text-align: right;
}

tfoot td:last-of-type {
  color: #e60000;
}


    </style>
</head>
<body>
    <table>
        <tr>
            <th>Stad</th>
            <th>Postcode</th>
            <th>straatnaam</th>
            <th>huisnummer</th>
            <th>bezorger</th>
        </tr>
        @foreach ($adressen as $adres)
        <tr>
            <td>{{ $adres->stad }}</td>
            <td>{{ $adres->postcode }}</td>
            <td>{{ $adres->straatnaam }}</td>
            <td>{{ $adres->huisnummer }}</td>
            <td>
                @if ($adres->route_id != null)
                    <p>@foreach ($users as $user)
                        @if ($user->id == $adres->route_id)
                            {{ $user->username }}
                        @endif
                        
                    @endforeach</p>
                @else
                    <form action='/planuser' method="POST">
                        @csrf
                        <select name="user_id" id="user_id">
                          @foreach ($users as $user)
                              <option value="{{ $user->id }}">{{ $user->username }}</option>
                          @endforeach
                        </select>
                        <input type="hidden" name="adres_id" value="{{ $adres->id }}">

                        <button type="submit">Plan</button>
                    </form>
                @endif
            </td>
        </tr>
        @endforeach 
</body>
</html>