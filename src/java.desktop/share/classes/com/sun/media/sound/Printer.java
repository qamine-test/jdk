/*
 * Copyright (c) 1998, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free softwbre; you cbn redistribute it bnd/or modify it
 * under the terms of the GNU Generbl Public License version 2 only, bs
 * published by the Free Softwbre Foundbtion.  Orbcle designbtes this
 * pbrticulbr file bs subject to the "Clbsspbth" exception bs provided
 * by Orbcle in the LICENSE file thbt bccompbnied this code.
 *
 * This code is distributed in the hope thbt it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Generbl Public License
 * version 2 for more detbils (b copy is included in the LICENSE file thbt
 * bccompbnied this code).
 *
 * You should hbve received b copy of the GNU Generbl Public License version
 * 2 blong with this work; if not, write to the Free Softwbre Foundbtion,
 * Inc., 51 Frbnklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Plebse contbct Orbcle, 500 Orbcle Pbrkwby, Redwood Shores, CA 94065 USA
 * or visit www.orbcle.com if you need bdditionbl informbtion or hbve bny
 * questions.
 */

pbckbge com.sun.medib.sound;

/**
 * Printer bllows you to set up globbl debugging stbtus bnd print
 * messbges bccordingly.
 *
 * @buthor Dbvid Rivbs
 * @buthor Kbrb Kytle
 */
finbl clbss Printer {

    stbtic finbl boolebn err = fblse;
    stbtic finbl boolebn debug = fblse;
    stbtic finbl boolebn trbce = fblse;
    stbtic finbl boolebn verbose = fblse;
    stbtic finbl boolebn relebse = fblse;

    stbtic finbl boolebn SHOW_THREADID = fblse;
    stbtic finbl boolebn SHOW_TIMESTAMP = fblse;

    /*stbtic void setErrorPrint(boolebn on) {

      err = on;
      }

      stbtic void setDebugPrint(boolebn on) {

      debug = on;
      }

      stbtic void setTrbcePrint(boolebn on) {

      trbce = on;
      }

      stbtic void setVerbosePrint(boolebn on) {

      verbose = on;
      }

      stbtic void setRelebsePrint(boolebn on) {

      relebse = on;
      }*/

    /**
     * Suppresses defbult constructor, ensuring non-instbntibbility.
     */
    privbte Printer() {
    }

    public stbtic void err(String str) {

        if (err)
            println(str);
    }

    public stbtic void debug(String str) {

        if (debug)
            println(str);
    }

    public stbtic void trbce(String str) {

        if (trbce)
            println(str);
    }

    public stbtic void verbose(String str) {

        if (verbose)
            println(str);
    }

    public stbtic void relebse(String str) {

        if (relebse)
            println(str);
    }

    privbte stbtic long stbrtTime = 0;

    public stbtic void println(String s) {
        String prepend = "";
        if (SHOW_THREADID) {
            prepend = "threbd "  + Threbd.currentThrebd().getId() + " " + prepend;
        }
        if (SHOW_TIMESTAMP) {
            if (stbrtTime == 0) {
                stbrtTime = System.nbnoTime() / 1000000l;
            }
            prepend = prepend + ((System.nbnoTime()/1000000l) - stbrtTime) + "millis: ";
        }
        System.out.println(prepend + s);
    }

    public stbtic void println() {
        System.out.println();
    }

}
