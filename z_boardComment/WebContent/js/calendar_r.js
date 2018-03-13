var Cal = function(divId) {

  //Store div id
  this.divId = divId;

  // Days of week, starting on Sunday
  this.DaysOfWeek = [
    'Sun',
    'Mon',
    'Tue',
    'Wed',
    'Thu',
    'Fri',
    'Sat'
  ];

  // Months, stating on January
  this.Months = [ 'January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December' ];

  // Set the current month, year
  var d = new Date();

  this.currMonth = d.getMonth();
  this.currYear = d.getFullYear();
  this.currDay = d.getDate();

};

// Goes to next month
Cal.prototype.nextMonth = function() {
  if (this.currMonth == 11) {
    this.currMonth = 0;
    this.currYear = this.currYear + 1;
  } else {
    this.currMonth = this.currMonth + 1;
  }
//this.showcurr();
};

// Goes to previous month
Cal.prototype.previousMonth = function() {
  if (this.currMonth == 0) {
    this.currMonth = 11;
    this.currYear = this.currYear - 1;
  } else {
    this.currMonth = this.currMonth - 1;
  }
//  this.showcurr();
};

// Show current month
Cal.prototype.showcurr = function(sobi) {
  this.showMonth(this.currYear, this.currMonth, sobi);
};

// Show month (year, month)
Cal.prototype.showMonth = function(y, m, sobi) {

  var d = new Date(),
    // First day of the week in the selected month
    firstDayOfMonth = new Date(y, m, 1).getDay(),
    // Last day of the selected month
    lastDateOfMonth = new Date(y, m + 1, 0).getDate(),
    // Last day of the previous month
    lastDayOfLastMonth = m == 0 ? new Date(y - 1, 11, 0).getDate() : new Date(y, m, 0).getDate();


  var html = '<table>';

  // Write selected month and year
  html += '<thead><tr>';
  html += '<td colspan="7">' + this.Months[m] + ' ' + y + '</td>';
  html += '</tr></thead>';


  // Write the header of the days of the week
  html += '<tr>';
  for (var i = 0; i < this.DaysOfWeek.length; i++) {
    html += '<td class="weeks">' + this.DaysOfWeek[i]+'</td>';
  }

  // Write the days
  var i = 1;
  do {

    var dow = new Date(y, m, i).getDay();

    // If Sunday, start new row
    if (dow == 0) {
      html += '<tr>';
    }
    // If not Sunday but first day of the month
    // it will write the last days from the previous month
    //신경 no
    else if (i == 1) {
      html += '<tr>';
      var k = lastDayOfLastMonth - firstDayOfMonth + 1;
      for (var j = 0; j < firstDayOfMonth; j++) {
        html += '<td class="not-current">' + k + '</td>';
        k++;
      }
    }
    //이부분에 뿌려주기 
    // Write the current day in the loop
    var chk = new Date();
    var chkY = chk.getFullYear();
    var chkM = chk.getMonth();


      if (chkY == this.currYear && chkM == this.currMonth && i == this.currDay) {
        html += '<td class="today">' + i;
        $.each(sobi, function(index, item) {
          dt = new Date(item.r_date);
          if (dt.getDate() == i) {
            html += '<br>' +'<a href="http://www.naver.com"><i class="won icon"></i>'+item.balance+'</a>';
          }

        });
        html += "</td>";

      } else {
        html += '<td class="normal">' + i;

        $.each(sobi, function(index, item) {
          dt = new Date(item.r_date);
          if (dt.getDate() == i) {
            html += '<br>' + '<a href="http://www.naver.com"><i class="won icon"></i>'+item.balance+'</a>';
            
          }

        });
        html += "</td>";
      }
      // If Saturday, closes the row

    

    // If Saturday, closes the row
    if (dow == 6) {
      html += '</tr>';
    }


    // If not Saturday, but last day of the selected month
    // it will write the next few days from the next month
    //신경 no
    else if (i == lastDateOfMonth) {
      var k = 1;
      for (dow; dow < 6; dow++) {
        html += '<td class="not-current">' + k + '</td>';
        k++;
      }
    }

    i++;
  } while (i <= lastDateOfMonth);

  // Closes table
  html += '</table>';

  // Write HTML to the div
  document.getElementById(this.divId).innerHTML = html;
};

// On Load of the window
window.onload = function() {

  
  // Start calendar
  var c = new Cal("divCal");
  var cd = new Date();



  var params = "currMonth=" + cd.getMonth() + "&currYear=" + cd.getFullYear();

  $.ajax({
    url : 'changeCalendar.do',
    data : params,
    dataType : 'json',
    success : function(sobi) {
      if (sobi) {
        c.showcurr(sobi);

      }
    }
  });



  // Bind next and previous button clicks




  $('#btnNext').on('click', function() {
    c.nextMonth();

    var params = "currMonth=" + c.currMonth + "&currYear=" + c.currYear;
    $.ajax({
      url : 'changeCalendar.do',
      data : params,
      dataType : 'json',
      success : function(sobi) {
        if (sobi) {

          c.showcurr(sobi);
        }
      }
    });



  });


  $('#btnPrev').on('click', function() {

    c.previousMonth();
    var params = "currMonth=" + c.currMonth + "&currYear=" + c.currYear;
    $.ajax({
      url : 'changeCalendar.do',
      data : params,
      dataType : 'json',
      success : function(sobi) {
        if (sobi) {
          c.showcurr(sobi);
        }
      }
    });



  });
}

// Get element by id
function getId(id) {
  return document.getElementById(id);
}