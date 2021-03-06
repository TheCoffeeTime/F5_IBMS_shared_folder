/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

// Note to myself replace coolOrange with if statement 

/*
 * Copyright (c) Ian F. Darwin, http://www.darwinsys.com/, 1996-2002.
 * All rights reserved. Software written by Ian F. Darwin and others.
 * $Id: LICENSE,v 1.8 2004/02/09 03:33:38 ian Exp $
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE AUTHOR AND CONTRIBUTORS ``AS IS''
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED
 * TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE AUTHOR OR CONTRIBUTORS
 * BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 * Java, the Duke mascot, and all variants of Sun's Java "steaming coffee
 * cup" logo are trademarks of Sun Microsystems. Sun's, and James Gosling's,
 * pioneering role in inventing and promulgating (and standardizing) the Java
 * language and environment is gratefully acknowledged.
 *
 * The pioneering role of Dennis Ritchie and Bjarne Stroustrup, of AT&T, for
 * inventing predecessor languages C and C++ is also gratefully acknowledged.
 */

//import working_folder.database;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.Font;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
/**
 * Bean to display a month calendar in a JPanel. Only works for the Western
 * calendar.
 *
 * @author Ian F. Darwin, http://www.darwinsys.com/
 * @version $Id: Cal.java,v 1.5 2004/02/09 03:33:45 ian Exp $
 */

//Modified by: Nikita. Last modified: 21/02/13
public class Cal extends JPanel {

    void addCompForBorder(Border border,
                          String description,
                          Container container) {
        JPanel comp = new JPanel(new GridLayout(1, 1), false);
        JLabel label = new JLabel(description, JLabel.CENTER);
        comp.add(label);
        comp.setBorder(border);

        container.add(Box.createRigidArea(new Dimension(0, 10)));
        container.add(comp);
    }
  /** The currently-interesting year (not modulo 1900!) */
  protected int yy;

  /** Currently-interesting month and day */
  protected int mm, dd;

  /** The buttons to be displayed */
  protected JButton labs[][];

  /** The number of day squares to leave blank at the start of this month */
  protected int leadGap = 0;

  /** A Calendar object used throughout */
  Calendar calendar = new GregorianCalendar();

  /** Today's year */
  protected final int thisYear = calendar.get(Calendar.YEAR);

  /** Today's month */
  protected final int thisMonth = calendar.get(Calendar.MONTH);

  /** One of the buttons. We just keep its reference for getBackground(). */
  private JButton b0;

  /** The month choice */
  private JComboBox monthChoice;

  /** The year choice */
  private JComboBox yearChoice;

  /** ADDED BY NIKITA */

  public GregorianCalendar getDate()
  {
      GregorianCalendar selectedDate = new GregorianCalendar(yy, mm, dd, 0, 0, 0);
      //System.out.println("YEAR: " + selectedDate.getYear());
      return selectedDate;
  }
  Color coolOrange = new Color(242, 161, 33);

  private static Color availableDays = new Color(107, 142, 35); // Green

  private static Color unavailableDays = new Color(176, 29, 29); // Red

  private static Color unusedDays = new Color (242, 161, 33); // Orange

  private static Color selectedDays = new Color (54, 101, 203); // Blue
  
  private static Color holidayDays = new Color (232, 255, 73); // Yellow

  /**
   * Construct a Cal, starting with today.
   */
  Cal(int driverID) {
    super();
    setYYMMDD(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH));
    buildGUI(driverID);
    recompute(driverID);

  }

  /**
   * Construct a Cal, given the leading days and the total days
   *
   * @exception IllegalArgumentException
   *                If year out of range
   */
  Cal(int year, int month, int today, int driverID) {
    super();
    setYYMMDD(year, month, today);
    buildGUI(driverID);
    recompute(driverID);
    //Date selectedDate = new Date(year, month, today, 0, 0, 0);
  }

  private void setYYMMDD(int year, int month, int today) {
    yy = year;
    mm = month;
    dd = today;
  }

  String[] months = { "January", "February", "March", "April", "May", "June",
      "July", "August", "September", "October", "November", "December" };

  /** Build the GUI. Assumes that setYYMMDD has been called. */
  private void buildGUI(final int driverID) {
    
    getAccessibleContext().setAccessibleDescription(
        "Calendar not accessible yet. Sorry!");
    //setBorder(BorderFactory.createEtchedBorder());

    setLayout(new BorderLayout());

    JPanel tp = new JPanel();
    tp.add(monthChoice = new JComboBox());
    for (int i = 0; i < months.length; i++)
      monthChoice.addItem(months[i]);
    monthChoice.setSelectedItem(months[mm]);
    monthChoice.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ae) {
        int i = monthChoice.getSelectedIndex();
        if (i >= 0) {
          mm = i;
          // System.out.println("Month=" + mm);
          recompute(driverID);
        }
      }
    });
    monthChoice.getAccessibleContext().setAccessibleName("Months");
    monthChoice.getAccessibleContext().setAccessibleDescription(
        "Choose a month of the year");

    tp.add(yearChoice = new JComboBox());
    yearChoice.setEditable(true);
    for (int i = yy; i < yy + 5; i++)
      yearChoice.addItem(Integer.toString(i));
    yearChoice.setSelectedItem(Integer.toString(yy));
    yearChoice.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent ae) {
        int i = yearChoice.getSelectedIndex();
        if (i >= 0) {
          yy = Integer.parseInt(yearChoice.getSelectedItem()
              .toString());
          // System.out.println("Year=" + yy);
          recompute(driverID);
        }
      }
    });
    add(BorderLayout.CENTER, tp);

    JPanel bp = new JPanel();
    bp.setLayout(new GridLayout(7, 7));
    labs = new JButton[6][7]; // first row is days

    bp.add(b0 = new JButton("S"));
    bp.add(new JButton("M"));
    bp.add(new JButton("T"));
    bp.add(new JButton("W"));
    bp.add(new JButton("R"));
    bp.add(new JButton("F"));
    bp.add(new JButton("S"));

    ActionListener dateSetter = new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        String num = e.getActionCommand();
        if (!num.equals("")) {
          // set the current day highlighted
          setDayActive(Integer.parseInt(num), driverID);
          // When this becomes a Bean, you can
          // fire some kind of DateChanged event here.
          // Also, build a similar daySetter for day-of-week btns.
        }
      }
    };

    // Construct all the buttons, and add them.
    for (int i = 0; i < 6; i++)
      for (int j = 0; j < 7; j++) {
        bp.add(labs[i][j] = new JButton(""));
        labs[i][j].addActionListener(dateSetter);
      }
    add(BorderLayout.SOUTH, bp);
  }

  public final static int dom[] = { 31, 28, 31, 30, /* jan feb mar apr */
  31, 30, 31, 31, /* may jun jul aug */
  30, 31, 30, 31 /* sep oct nov dec */
  };

  /** Compute which days to put where, in the Cal panel */
  protected void recompute(int driverID) {
    // System.out.println("Cal::recompute: " + yy + ":" + mm + ":" + dd);
    if (mm < 0 || mm > 11)
      throw new IllegalArgumentException("Month " + mm
          + " bad, must be 0-11");
    clearDayActive(driverID);
    calendar = new GregorianCalendar(yy, mm, dd);

    // Compute how much to leave before the first.
    // getDay() returns 0 for Sunday, which is just right.
    leadGap = new GregorianCalendar(yy, mm, 1).get(Calendar.DAY_OF_WEEK) - 1;
    // System.out.println("leadGap = " + leadGap);

    int daysInMonth = dom[mm];
    if (isLeap(calendar.get(Calendar.YEAR)) && mm == 1)
//    if (isLeap(calendar.get(Calendar.YEAR)) && mm > 1)
      ++daysInMonth;
    //System.out.println("test " + daysInMonth);
    // Blank out the labels before 1st day of month
    for (int i = 0; i < leadGap; i++) {
      labs[0][i].setText("");
      labs[0][i].setBackground(unusedDays);
    }
    GregorianCalendar dateToCheck = new GregorianCalendar(yy, mm, dd, 0, 0, 0);
    // Fill in numbers for the day of month.
    for (int i = 1; i <= daysInMonth; i++) {
      JButton b = labs[(leadGap + i - 1) / 7][(leadGap + i - 1) % 7];
      b.setText(Integer.toString(i));
      // ADDED BY ME
      b.setBackground(availableDays);
      
      dateToCheck = new GregorianCalendar(yy, mm, i, 0, 0, 0);
      if (ValidateHolidayRequest.dateAvailable(dateToCheck, driverID) == 1)
      {
        b.setBackground(availableDays);
        System.out.println("Checking date " + "yy: " + yy + " mm: " + mm + " dd: " + i + " TRUE");
      }
      else
      {
        if (ValidateHolidayRequest.dateAvailable(dateToCheck, driverID) == 0)
        {
          b.setBackground(unavailableDays);
          System.out.println("Checking date " + "yy: " + yy + " mm: " + mm + " dd: " + i + " FALSE");
        }
        else
        {
            b.setBackground(holidayDays);
        }
      }
      //dateAvailable(dateToCheck) == true

      /* CHANGE BACKGROUND IF DATE IS AVAILABLE
      Date dateToCheck = new Date(yy, mm, dd, 0, 0, 0);
      if(ValidateHolidayRequest.dateAvailable(dateToCheck) == true)
      {
        b.setBackground(coolOrange);
      }
      else
      {
        b.setBackground(Color.red);
      }
       */
    }

    // 7 days/week * up to 6 rows
    //for (int i = leadGap + 1 + daysInMonth; i < 6 * 7; i++) {
    for (int i = leadGap + daysInMonth; i < 6 * 7; i++) {
      labs[(i) / 7][(i) % 7].setText("");
      labs[(i) / 7][(i) % 7].setBackground(unusedDays);
    }

    // Shade current day, only if current month
    if (thisYear == yy && mm == thisMonth)
      setDayActive(dd, driverID); // shade the box for today

    // Say we need to be drawn on the screen
    repaint();
  }

  /**
   * isLeap() returns true if the given year is a Leap Year.
   *
   * "a year is a leap year if it is divisible by 4 but not by 100, except
   * that years divisible by 400 *are* leap years." -- Kernighan & Ritchie,
   * _The C Programming Language_, p 37.
   */
  public boolean isLeap(int year) {
    if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0)
      return true;
    return false;
  }

  /** Set the year, month, and day */
  public void setDate(int yy, int mm, int dd, int driverID) {
    // System.out.println("Cal::setDate");
    this.yy = yy;
    this.mm = mm; // starts at 0, like Date
    this.dd = dd;
    recompute(driverID);
  }

  /** Unset any previously highlighted day */
  private void clearDayActive(int driverID) {
    JButton b;

    // First un-shade the previously-selected square, if any
    if (activeDay > 0) {
      b = labs[(leadGap + activeDay - 1) / 7][(leadGap + activeDay - 1) % 7];
      //b.setBackground(b0.getBackground());
      GregorianCalendar dateToCheck = new GregorianCalendar(yy, mm, activeDay, 0, 0, 0);
      
      if (ValidateHolidayRequest.dateAvailable(dateToCheck, driverID) == 1)
      {
        b.setBackground(availableDays);
      }
      else
      {
        if (ValidateHolidayRequest.dateAvailable(dateToCheck, driverID) == 0)
        {
          b.setBackground(unavailableDays);
        }
        else
        {
          b.setBackground(holidayDays);
        }
      }
      b.repaint();
      activeDay = -1;
    }
  }

  private int activeDay = -1;

  /** Set just the day, on the current month */
  public void setDayActive(int newDay, int driverID) {

    clearDayActive(driverID);

    // Set the new one
    if (newDay <= 0)
      dd = new GregorianCalendar().get(Calendar.DAY_OF_MONTH);
    else
      dd = newDay;
    // Now shade the correct square
    Component square = labs[(leadGap + newDay - 1) / 7][(leadGap + newDay - 1) % 7];
    square.setBackground(selectedDays);
    square.repaint();
    activeDay = newDay;
  }
}
