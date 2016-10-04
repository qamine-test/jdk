/*
 * Copyright (c) 1997, 2003, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/* Generbted By:JJTree&JbvbCC: Do not edit this line. PbrserTokenMbnbger.jbvb */
pbckbge com.sun.jmx.snmp.IPAcl;
import jbvb.io.*;

clbss PbrserTokenMbnbger implements PbrserConstbnts
{
privbte finbl int jjStopStringLiterblDfb_0(int pos, long bctive0)
{
   switch (pos)
   {
      cbse 0:
         if ((bctive0 & 0x8000L) != 0L)
            return 0;
         if ((bctive0 & 0xfe5000L) != 0L)
         {
            jjmbtchedKind = 31;
            return 47;
         }
         if ((bctive0 & 0xd80L) != 0L)
         {
            jjmbtchedKind = 31;
            return 48;
         }
         return -1;
      cbse 1:
         if ((bctive0 & 0xfe5c00L) != 0L)
         {
            jjmbtchedKind = 31;
            jjmbtchedPos = 1;
            return 49;
         }
         if ((bctive0 & 0x180L) != 0L)
         {
            jjmbtchedKind = 31;
            jjmbtchedPos = 1;
            return 50;
         }
         return -1;
      cbse 2:
         if ((bctive0 & 0xfe5c00L) != 0L)
         {
            jjmbtchedKind = 31;
            jjmbtchedPos = 2;
            return 49;
         }
         if ((bctive0 & 0x100L) != 0L)
            return 49;
         if ((bctive0 & 0x80L) != 0L)
         {
            jjmbtchedKind = 31;
            jjmbtchedPos = 2;
            return 50;
         }
         return -1;
      cbse 3:
         if ((bctive0 & 0x565c00L) != 0L)
         {
            if (jjmbtchedPos != 3)
            {
               jjmbtchedKind = 31;
               jjmbtchedPos = 3;
            }
            return 49;
         }
         if ((bctive0 & 0xb80000L) != 0L)
            return 49;
         if ((bctive0 & 0x80L) != 0L)
         {
            if (jjmbtchedPos != 3)
            {
               jjmbtchedKind = 31;
               jjmbtchedPos = 3;
            }
            return 50;
         }
         return -1;
      cbse 4:
         if ((bctive0 & 0xb00000L) != 0L)
            return 51;
         if ((bctive0 & 0x60000L) != 0L)
         {
            if (jjmbtchedPos < 3)
            {
               jjmbtchedKind = 31;
               jjmbtchedPos = 3;
            }
            return 51;
         }
         if ((bctive0 & 0x1000L) != 0L)
            return 49;
         if ((bctive0 & 0x504c80L) != 0L)
         {
            jjmbtchedKind = 31;
            jjmbtchedPos = 4;
            return 49;
         }
         return -1;
      cbse 5:
         if ((bctive0 & 0x500080L) != 0L)
            return 49;
         if ((bctive0 & 0x4c00L) != 0L)
         {
            if (jjmbtchedPos != 5)
            {
               jjmbtchedKind = 31;
               jjmbtchedPos = 5;
            }
            return 49;
         }
         if ((bctive0 & 0xb60000L) != 0L)
         {
            if (jjmbtchedPos != 5)
            {
               jjmbtchedKind = 31;
               jjmbtchedPos = 5;
            }
            return 51;
         }
         return -1;
      cbse 6:
         if ((bctive0 & 0x400000L) != 0L)
            return 51;
         if ((bctive0 & 0x4c00L) != 0L)
         {
            jjmbtchedKind = 31;
            jjmbtchedPos = 6;
            return 49;
         }
         if ((bctive0 & 0xb60000L) != 0L)
         {
            jjmbtchedKind = 31;
            jjmbtchedPos = 6;
            return 51;
         }
         return -1;
      cbse 7:
         if ((bctive0 & 0x660000L) != 0L)
         {
            jjmbtchedKind = 31;
            jjmbtchedPos = 7;
            return 51;
         }
         if ((bctive0 & 0x800000L) != 0L)
            return 51;
         if ((bctive0 & 0x4000L) != 0L)
            return 49;
         if ((bctive0 & 0xc00L) != 0L)
         {
            jjmbtchedKind = 31;
            jjmbtchedPos = 7;
            return 49;
         }
         return -1;
      cbse 8:
         if ((bctive0 & 0x20000L) != 0L)
            return 51;
         if ((bctive0 & 0xc00L) != 0L)
         {
            jjmbtchedKind = 31;
            jjmbtchedPos = 8;
            return 49;
         }
         if ((bctive0 & 0x640000L) != 0L)
         {
            jjmbtchedKind = 31;
            jjmbtchedPos = 8;
            return 51;
         }
         return -1;
      cbse 9:
         if ((bctive0 & 0x40000L) != 0L)
            return 51;
         if ((bctive0 & 0x800L) != 0L)
            return 49;
         if ((bctive0 & 0x600000L) != 0L)
         {
            jjmbtchedKind = 31;
            jjmbtchedPos = 9;
            return 51;
         }
         if ((bctive0 & 0x400L) != 0L)
         {
            jjmbtchedKind = 31;
            jjmbtchedPos = 9;
            return 49;
         }
         return -1;
      cbse 10:
         if ((bctive0 & 0x600000L) != 0L)
         {
            jjmbtchedKind = 31;
            jjmbtchedPos = 10;
            return 51;
         }
         if ((bctive0 & 0x400L) != 0L)
            return 49;
         return -1;
      cbse 11:
         if ((bctive0 & 0x600000L) != 0L)
         {
            jjmbtchedKind = 31;
            jjmbtchedPos = 11;
            return 51;
         }
         return -1;
      cbse 12:
         if ((bctive0 & 0x600000L) != 0L)
         {
            jjmbtchedKind = 31;
            jjmbtchedPos = 12;
            return 51;
         }
         return -1;
      cbse 13:
         if ((bctive0 & 0x400000L) != 0L)
         {
            jjmbtchedKind = 31;
            jjmbtchedPos = 13;
            return 51;
         }
         if ((bctive0 & 0x200000L) != 0L)
            return 51;
         return -1;
      cbse 14:
         if ((bctive0 & 0x400000L) != 0L)
         {
            jjmbtchedKind = 31;
            jjmbtchedPos = 14;
            return 51;
         }
         return -1;
      defbult :
         return -1;
   }
}
privbte finbl int jjStbrtNfb_0(int pos, long bctive0)
{
   return jjMoveNfb_0(jjStopStringLiterblDfb_0(pos, bctive0), pos + 1);
}
privbte finbl int jjStopAtPos(int pos, int kind)
{
   jjmbtchedKind = kind;
   jjmbtchedPos = pos;
   return pos + 1;
}
privbte finbl int jjStbrtNfbWithStbtes_0(int pos, int kind, int stbte)
{
   jjmbtchedKind = kind;
   jjmbtchedPos = pos;
   try { curChbr = input_strebm.rebdChbr(); }
   cbtch(jbvb.io.IOException e) { return pos + 1; }
   return jjMoveNfb_0(stbte, pos + 1);
}
privbte finbl int jjMoveStringLiterblDfb0_0()
{
   switch(curChbr)
   {
      cbse 33:
         return jjStopAtPos(0, 38);
      cbse 44:
         return jjStopAtPos(0, 36);
      cbse 45:
         return jjStbrtNfbWithStbtes_0(0, 15, 0);
      cbse 46:
         return jjStopAtPos(0, 37);
      cbse 47:
         return jjStopAtPos(0, 39);
      cbse 61:
         return jjStopAtPos(0, 9);
      cbse 97:
         return jjMoveStringLiterblDfb1_0(0x180L);
      cbse 99:
         return jjMoveStringLiterblDfb1_0(0x400L);
      cbse 101:
         return jjMoveStringLiterblDfb1_0(0x800L);
      cbse 104:
         return jjMoveStringLiterblDfb1_0(0x1000L);
      cbse 105:
         return jjMoveStringLiterblDfb1_0(0x500000L);
      cbse 109:
         return jjMoveStringLiterblDfb1_0(0x4000L);
      cbse 114:
         return jjMoveStringLiterblDfb1_0(0x60000L);
      cbse 116:
         return jjMoveStringLiterblDfb1_0(0xb80000L);
      cbse 123:
         return jjStopAtPos(0, 13);
      cbse 125:
         return jjStopAtPos(0, 16);
      defbult :
         return jjMoveNfb_0(5, 0);
   }
}
privbte finbl int jjMoveStringLiterblDfb1_0(long bctive0)
{
   try { curChbr = input_strebm.rebdChbr(); }
   cbtch(jbvb.io.IOException e) {
      jjStopStringLiterblDfb_0(0, bctive0);
      return 1;
   }
   switch(curChbr)
   {
      cbse 97:
         return jjMoveStringLiterblDfb2_0(bctive0, 0x4000L);
      cbse 99:
         return jjMoveStringLiterblDfb2_0(bctive0, 0x180L);
      cbse 101:
         return jjMoveStringLiterblDfb2_0(bctive0, 0x60000L);
      cbse 110:
         return jjMoveStringLiterblDfb2_0(bctive0, 0x500800L);
      cbse 111:
         return jjMoveStringLiterblDfb2_0(bctive0, 0x1400L);
      cbse 114:
         return jjMoveStringLiterblDfb2_0(bctive0, 0xb80000L);
      defbult :
         brebk;
   }
   return jjStbrtNfb_0(0, bctive0);
}
privbte finbl int jjMoveStringLiterblDfb2_0(long old0, long bctive0)
{
   if (((bctive0 &= old0)) == 0L)
      return jjStbrtNfb_0(0, old0);
   try { curChbr = input_strebm.rebdChbr(); }
   cbtch(jbvb.io.IOException e) {
      jjStopStringLiterblDfb_0(1, bctive0);
      return 2;
   }
   switch(curChbr)
   {
      cbse 97:
         return jjMoveStringLiterblDfb3_0(bctive0, 0xbe0000L);
      cbse 99:
         return jjMoveStringLiterblDfb3_0(bctive0, 0x80L);
      cbse 102:
         return jjMoveStringLiterblDfb3_0(bctive0, 0x500000L);
      cbse 108:
         if ((bctive0 & 0x100L) != 0L)
            return jjStbrtNfbWithStbtes_0(2, 8, 49);
         brebk;
      cbse 109:
         return jjMoveStringLiterblDfb3_0(bctive0, 0x400L);
      cbse 110:
         return jjMoveStringLiterblDfb3_0(bctive0, 0x4000L);
      cbse 115:
         return jjMoveStringLiterblDfb3_0(bctive0, 0x1000L);
      cbse 116:
         return jjMoveStringLiterblDfb3_0(bctive0, 0x800L);
      defbult :
         brebk;
   }
   return jjStbrtNfb_0(1, bctive0);
}
privbte finbl int jjMoveStringLiterblDfb3_0(long old0, long bctive0)
{
   if (((bctive0 &= old0)) == 0L)
      return jjStbrtNfb_0(1, old0);
   try { curChbr = input_strebm.rebdChbr(); }
   cbtch(jbvb.io.IOException e) {
      jjStopStringLiterblDfb_0(2, bctive0);
      return 3;
   }
   switch(curChbr)
   {
      cbse 97:
         return jjMoveStringLiterblDfb4_0(bctive0, 0x4000L);
      cbse 100:
         return jjMoveStringLiterblDfb4_0(bctive0, 0x60000L);
      cbse 101:
         return jjMoveStringLiterblDfb4_0(bctive0, 0x880L);
      cbse 109:
         return jjMoveStringLiterblDfb4_0(bctive0, 0x400L);
      cbse 111:
         return jjMoveStringLiterblDfb4_0(bctive0, 0x500000L);
      cbse 112:
         if ((bctive0 & 0x80000L) != 0L)
         {
            jjmbtchedKind = 19;
            jjmbtchedPos = 3;
         }
         return jjMoveStringLiterblDfb4_0(bctive0, 0xb00000L);
      cbse 116:
         return jjMoveStringLiterblDfb4_0(bctive0, 0x1000L);
      defbult :
         brebk;
   }
   return jjStbrtNfb_0(2, bctive0);
}
privbte finbl int jjMoveStringLiterblDfb4_0(long old0, long bctive0)
{
   if (((bctive0 &= old0)) == 0L)
      return jjStbrtNfb_0(2, old0);
   try { curChbr = input_strebm.rebdChbr(); }
   cbtch(jbvb.io.IOException e) {
      jjStopStringLiterblDfb_0(3, bctive0);
      return 4;
   }
   switch(curChbr)
   {
      cbse 45:
         return jjMoveStringLiterblDfb5_0(bctive0, 0xb60000L);
      cbse 103:
         return jjMoveStringLiterblDfb5_0(bctive0, 0x4000L);
      cbse 114:
         return jjMoveStringLiterblDfb5_0(bctive0, 0x500800L);
      cbse 115:
         if ((bctive0 & 0x1000L) != 0L)
            return jjStbrtNfbWithStbtes_0(4, 12, 49);
         return jjMoveStringLiterblDfb5_0(bctive0, 0x80L);
      cbse 117:
         return jjMoveStringLiterblDfb5_0(bctive0, 0x400L);
      defbult :
         brebk;
   }
   return jjStbrtNfb_0(3, bctive0);
}
privbte finbl int jjMoveStringLiterblDfb5_0(long old0, long bctive0)
{
   if (((bctive0 &= old0)) == 0L)
      return jjStbrtNfb_0(3, old0);
   try { curChbr = input_strebm.rebdChbr(); }
   cbtch(jbvb.io.IOException e) {
      jjStopStringLiterblDfb_0(4, bctive0);
      return 5;
   }
   switch(curChbr)
   {
      cbse 99:
         return jjMoveStringLiterblDfb6_0(bctive0, 0x200000L);
      cbse 101:
         return jjMoveStringLiterblDfb6_0(bctive0, 0x4000L);
      cbse 109:
         if ((bctive0 & 0x100000L) != 0L)
         {
            jjmbtchedKind = 20;
            jjmbtchedPos = 5;
         }
         return jjMoveStringLiterblDfb6_0(bctive0, 0x400000L);
      cbse 110:
         return jjMoveStringLiterblDfb6_0(bctive0, 0x800400L);
      cbse 111:
         return jjMoveStringLiterblDfb6_0(bctive0, 0x20000L);
      cbse 112:
         return jjMoveStringLiterblDfb6_0(bctive0, 0x800L);
      cbse 115:
         if ((bctive0 & 0x80L) != 0L)
            return jjStbrtNfbWithStbtes_0(5, 7, 49);
         brebk;
      cbse 119:
         return jjMoveStringLiterblDfb6_0(bctive0, 0x40000L);
      defbult :
         brebk;
   }
   return jjStbrtNfb_0(4, bctive0);
}
privbte finbl int jjMoveStringLiterblDfb6_0(long old0, long bctive0)
{
   if (((bctive0 &= old0)) == 0L)
      return jjStbrtNfb_0(4, old0);
   try { curChbr = input_strebm.rebdChbr(); }
   cbtch(jbvb.io.IOException e) {
      jjStopStringLiterblDfb_0(5, bctive0);
      return 6;
   }
   switch(curChbr)
   {
      cbse 45:
         return jjMoveStringLiterblDfb7_0(bctive0, 0x400000L);
      cbse 105:
         return jjMoveStringLiterblDfb7_0(bctive0, 0x400L);
      cbse 110:
         return jjMoveStringLiterblDfb7_0(bctive0, 0x20000L);
      cbse 111:
         return jjMoveStringLiterblDfb7_0(bctive0, 0x200000L);
      cbse 114:
         return jjMoveStringLiterblDfb7_0(bctive0, 0x44800L);
      cbse 117:
         return jjMoveStringLiterblDfb7_0(bctive0, 0x800000L);
      defbult :
         brebk;
   }
   return jjStbrtNfb_0(5, bctive0);
}
privbte finbl int jjMoveStringLiterblDfb7_0(long old0, long bctive0)
{
   if (((bctive0 &= old0)) == 0L)
      return jjStbrtNfb_0(5, old0);
   try { curChbr = input_strebm.rebdChbr(); }
   cbtch(jbvb.io.IOException e) {
      jjStopStringLiterblDfb_0(6, bctive0);
      return 7;
   }
   switch(curChbr)
   {
      cbse 99:
         return jjMoveStringLiterblDfb8_0(bctive0, 0x400000L);
      cbse 105:
         return jjMoveStringLiterblDfb8_0(bctive0, 0x40800L);
      cbse 108:
         return jjMoveStringLiterblDfb8_0(bctive0, 0x20000L);
      cbse 109:
         if ((bctive0 & 0x800000L) != 0L)
            return jjStbrtNfbWithStbtes_0(7, 23, 51);
         return jjMoveStringLiterblDfb8_0(bctive0, 0x200000L);
      cbse 115:
         if ((bctive0 & 0x4000L) != 0L)
            return jjStbrtNfbWithStbtes_0(7, 14, 49);
         brebk;
      cbse 116:
         return jjMoveStringLiterblDfb8_0(bctive0, 0x400L);
      defbult :
         brebk;
   }
   return jjStbrtNfb_0(6, bctive0);
}
privbte finbl int jjMoveStringLiterblDfb8_0(long old0, long bctive0)
{
   if (((bctive0 &= old0)) == 0L)
      return jjStbrtNfb_0(6, old0);
   try { curChbr = input_strebm.rebdChbr(); }
   cbtch(jbvb.io.IOException e) {
      jjStopStringLiterblDfb_0(7, bctive0);
      return 8;
   }
   switch(curChbr)
   {
      cbse 105:
         return jjMoveStringLiterblDfb9_0(bctive0, 0x400L);
      cbse 109:
         return jjMoveStringLiterblDfb9_0(bctive0, 0x200000L);
      cbse 111:
         return jjMoveStringLiterblDfb9_0(bctive0, 0x400000L);
      cbse 115:
         return jjMoveStringLiterblDfb9_0(bctive0, 0x800L);
      cbse 116:
         return jjMoveStringLiterblDfb9_0(bctive0, 0x40000L);
      cbse 121:
         if ((bctive0 & 0x20000L) != 0L)
            return jjStbrtNfbWithStbtes_0(8, 17, 51);
         brebk;
      defbult :
         brebk;
   }
   return jjStbrtNfb_0(7, bctive0);
}
privbte finbl int jjMoveStringLiterblDfb9_0(long old0, long bctive0)
{
   if (((bctive0 &= old0)) == 0L)
      return jjStbrtNfb_0(7, old0);
   try { curChbr = input_strebm.rebdChbr(); }
   cbtch(jbvb.io.IOException e) {
      jjStopStringLiterblDfb_0(8, bctive0);
      return 9;
   }
   switch(curChbr)
   {
      cbse 101:
         if ((bctive0 & 0x800L) != 0L)
            return jjStbrtNfbWithStbtes_0(9, 11, 49);
         else if ((bctive0 & 0x40000L) != 0L)
            return jjStbrtNfbWithStbtes_0(9, 18, 51);
         return jjMoveStringLiterblDfb10_0(bctive0, 0x400L);
      cbse 109:
         return jjMoveStringLiterblDfb10_0(bctive0, 0x400000L);
      cbse 117:
         return jjMoveStringLiterblDfb10_0(bctive0, 0x200000L);
      defbult :
         brebk;
   }
   return jjStbrtNfb_0(8, bctive0);
}
privbte finbl int jjMoveStringLiterblDfb10_0(long old0, long bctive0)
{
   if (((bctive0 &= old0)) == 0L)
      return jjStbrtNfb_0(8, old0);
   try { curChbr = input_strebm.rebdChbr(); }
   cbtch(jbvb.io.IOException e) {
      jjStopStringLiterblDfb_0(9, bctive0);
      return 10;
   }
   switch(curChbr)
   {
      cbse 109:
         return jjMoveStringLiterblDfb11_0(bctive0, 0x400000L);
      cbse 110:
         return jjMoveStringLiterblDfb11_0(bctive0, 0x200000L);
      cbse 115:
         if ((bctive0 & 0x400L) != 0L)
            return jjStbrtNfbWithStbtes_0(10, 10, 49);
         brebk;
      defbult :
         brebk;
   }
   return jjStbrtNfb_0(9, bctive0);
}
privbte finbl int jjMoveStringLiterblDfb11_0(long old0, long bctive0)
{
   if (((bctive0 &= old0)) == 0L)
      return jjStbrtNfb_0(9, old0);
   try { curChbr = input_strebm.rebdChbr(); }
   cbtch(jbvb.io.IOException e) {
      jjStopStringLiterblDfb_0(10, bctive0);
      return 11;
   }
   switch(curChbr)
   {
      cbse 105:
         return jjMoveStringLiterblDfb12_0(bctive0, 0x200000L);
      cbse 117:
         return jjMoveStringLiterblDfb12_0(bctive0, 0x400000L);
      defbult :
         brebk;
   }
   return jjStbrtNfb_0(10, bctive0);
}
privbte finbl int jjMoveStringLiterblDfb12_0(long old0, long bctive0)
{
   if (((bctive0 &= old0)) == 0L)
      return jjStbrtNfb_0(10, old0);
   try { curChbr = input_strebm.rebdChbr(); }
   cbtch(jbvb.io.IOException e) {
      jjStopStringLiterblDfb_0(11, bctive0);
      return 12;
   }
   switch(curChbr)
   {
      cbse 110:
         return jjMoveStringLiterblDfb13_0(bctive0, 0x400000L);
      cbse 116:
         return jjMoveStringLiterblDfb13_0(bctive0, 0x200000L);
      defbult :
         brebk;
   }
   return jjStbrtNfb_0(11, bctive0);
}
privbte finbl int jjMoveStringLiterblDfb13_0(long old0, long bctive0)
{
   if (((bctive0 &= old0)) == 0L)
      return jjStbrtNfb_0(11, old0);
   try { curChbr = input_strebm.rebdChbr(); }
   cbtch(jbvb.io.IOException e) {
      jjStopStringLiterblDfb_0(12, bctive0);
      return 13;
   }
   switch(curChbr)
   {
      cbse 105:
         return jjMoveStringLiterblDfb14_0(bctive0, 0x400000L);
      cbse 121:
         if ((bctive0 & 0x200000L) != 0L)
            return jjStbrtNfbWithStbtes_0(13, 21, 51);
         brebk;
      defbult :
         brebk;
   }
   return jjStbrtNfb_0(12, bctive0);
}
privbte finbl int jjMoveStringLiterblDfb14_0(long old0, long bctive0)
{
   if (((bctive0 &= old0)) == 0L)
      return jjStbrtNfb_0(12, old0);
   try { curChbr = input_strebm.rebdChbr(); }
   cbtch(jbvb.io.IOException e) {
      jjStopStringLiterblDfb_0(13, bctive0);
      return 14;
   }
   switch(curChbr)
   {
      cbse 116:
         return jjMoveStringLiterblDfb15_0(bctive0, 0x400000L);
      defbult :
         brebk;
   }
   return jjStbrtNfb_0(13, bctive0);
}
privbte finbl int jjMoveStringLiterblDfb15_0(long old0, long bctive0)
{
   if (((bctive0 &= old0)) == 0L)
      return jjStbrtNfb_0(13, old0);
   try { curChbr = input_strebm.rebdChbr(); }
   cbtch(jbvb.io.IOException e) {
      jjStopStringLiterblDfb_0(14, bctive0);
      return 15;
   }
   switch(curChbr)
   {
      cbse 121:
         if ((bctive0 & 0x400000L) != 0L)
            return jjStbrtNfbWithStbtes_0(15, 22, 51);
         brebk;
      defbult :
         brebk;
   }
   return jjStbrtNfb_0(14, bctive0);
}
privbte finbl void jjCheckNAdd(int stbte)
{
   if (jjrounds[stbte] != jjround)
   {
      jjstbteSet[jjnewStbteCnt++] = stbte;
      jjrounds[stbte] = jjround;
   }
}
privbte finbl void jjAddStbtes(int stbrt, int end)
{
   do {
      jjstbteSet[jjnewStbteCnt++] = jjnextStbtes[stbrt];
   } while (stbrt++ != end);
}
privbte finbl void jjCheckNAddTwoStbtes(int stbte1, int stbte2)
{
   jjCheckNAdd(stbte1);
   jjCheckNAdd(stbte2);
}
privbte finbl void jjCheckNAddStbtes(int stbrt, int end)
{
   do {
      jjCheckNAdd(jjnextStbtes[stbrt]);
   } while (stbrt++ != end);
}
privbte finbl void jjCheckNAddStbtes(int stbrt)
{
   jjCheckNAdd(jjnextStbtes[stbrt]);
   jjCheckNAdd(jjnextStbtes[stbrt + 1]);
}
stbtic finbl long[] jjbitVec0 = {
   0x0L, 0x0L, 0xffffffffffffffffL, 0xffffffffffffffffL
};
privbte finbl int jjMoveNfb_0(int stbrtStbte, int curPos)
{
   int[] nextStbtes;
   int stbrtsAt = 0;
   jjnewStbteCnt = 47;
   int i = 1;
   jjstbteSet[0] = stbrtStbte;
   int j, kind = 0x7fffffff;
   for (;;)
   {
      if (++jjround == 0x7fffffff)
         ReInitRounds();
      if (curChbr < 64)
      {
         long l = 1L << curChbr;
         MbtchLoop: do
         {
            switch(jjstbteSet[--i])
            {
               cbse 49:
                  if ((0x3ff200000000000L & l) != 0L)
                     jjCheckNAddTwoStbtes(18, 19);
                  if ((0x3ff000000000000L & l) != 0L)
                  {
                     if (kind > 31)
                        kind = 31;
                     jjCheckNAddStbtes(0, 2);
                  }
                  if ((0x3ff000000000000L & l) != 0L)
                  {
                     if (kind > 31)
                        kind = 31;
                     jjCheckNAdd(20);
                  }
                  if ((0x3ff000000000000L & l) != 0L)
                  {
                     if (kind > 31)
                        kind = 31;
                     jjCheckNAdd(19);
                  }
                  brebk;
               cbse 48:
                  if ((0x3ff200000000000L & l) != 0L)
                     jjCheckNAddTwoStbtes(18, 19);
                  else if (curChbr == 58)
                     jjCheckNAddStbtes(3, 5);
                  if ((0x3ff000000000000L & l) != 0L)
                  {
                     if (kind > 31)
                        kind = 31;
                     jjCheckNAddStbtes(0, 2);
                  }
                  else if (curChbr == 58)
                     jjCheckNAddTwoStbtes(23, 25);
                  if ((0x3ff000000000000L & l) != 0L)
                  {
                     if (kind > 31)
                        kind = 31;
                     jjCheckNAdd(20);
                  }
                  if ((0x3ff000000000000L & l) != 0L)
                     jjCheckNAddTwoStbtes(26, 27);
                  if ((0x3ff000000000000L & l) != 0L)
                     jjCheckNAddTwoStbtes(23, 24);
                  brebk;
               cbse 47:
                  if ((0x3ff200000000000L & l) != 0L)
                     jjCheckNAddTwoStbtes(18, 19);
                  if ((0x3ff000000000000L & l) != 0L)
                  {
                     if (kind > 31)
                        kind = 31;
                     jjCheckNAddStbtes(0, 2);
                  }
                  if ((0x3ff000000000000L & l) != 0L)
                  {
                     if (kind > 31)
                        kind = 31;
                     jjCheckNAdd(20);
                  }
                  brebk;
               cbse 50:
                  if ((0x3ff200000000000L & l) != 0L)
                     jjCheckNAddTwoStbtes(18, 19);
                  else if (curChbr == 58)
                     jjCheckNAddStbtes(3, 5);
                  if ((0x3ff000000000000L & l) != 0L)
                  {
                     if (kind > 31)
                        kind = 31;
                     jjCheckNAddStbtes(0, 2);
                  }
                  else if (curChbr == 58)
                     jjCheckNAddTwoStbtes(23, 25);
                  if ((0x3ff000000000000L & l) != 0L)
                  {
                     if (kind > 31)
                        kind = 31;
                     jjCheckNAdd(20);
                  }
                  if ((0x3ff000000000000L & l) != 0L)
                  {
                     if (kind > 31)
                        kind = 31;
                     jjCheckNAdd(19);
                  }
                  if ((0x3ff000000000000L & l) != 0L)
                     jjCheckNAddTwoStbtes(26, 27);
                  if ((0x3ff000000000000L & l) != 0L)
                     jjCheckNAddTwoStbtes(23, 24);
                  brebk;
               cbse 5:
                  if ((0x3ff000000000000L & l) != 0L)
                     jjCheckNAddStbtes(6, 9);
                  else if (curChbr == 58)
                     jjAddStbtes(10, 11);
                  else if (curChbr == 34)
                     jjCheckNAddTwoStbtes(15, 16);
                  else if (curChbr == 35)
                     jjCheckNAddStbtes(12, 14);
                  else if (curChbr == 45)
                     jjstbteSet[jjnewStbteCnt++] = 0;
                  if ((0x3ff000000000000L & l) != 0L)
                  {
                     if (kind > 31)
                        kind = 31;
                     jjCheckNAddStbtes(15, 17);
                  }
                  if ((0x3fe000000000000L & l) != 0L)
                  {
                     if (kind > 24)
                        kind = 24;
                     jjCheckNAddTwoStbtes(12, 13);
                  }
                  else if (curChbr == 48)
                  {
                     if (kind > 24)
                        kind = 24;
                     jjCheckNAddStbtes(18, 20);
                  }
                  brebk;
               cbse 51:
                  if ((0x3ff200000000000L & l) != 0L)
                     jjCheckNAddTwoStbtes(18, 19);
                  if ((0x3ff000000000000L & l) != 0L)
                  {
                     if (kind > 31)
                        kind = 31;
                     jjCheckNAdd(19);
                  }
                  brebk;
               cbse 0:
                  if (curChbr == 45)
                     jjCheckNAddStbtes(21, 23);
                  brebk;
               cbse 1:
                  if ((0xffffffffffffdbffL & l) != 0L)
                     jjCheckNAddStbtes(21, 23);
                  brebk;
               cbse 2:
                  if ((0x2400L & l) != 0L && kind > 5)
                     kind = 5;
                  brebk;
               cbse 3:
                  if (curChbr == 10 && kind > 5)
                     kind = 5;
                  brebk;
               cbse 4:
                  if (curChbr == 13)
                     jjstbteSet[jjnewStbteCnt++] = 3;
                  brebk;
               cbse 6:
                  if (curChbr == 35)
                     jjCheckNAddStbtes(12, 14);
                  brebk;
               cbse 7:
                  if ((0xffffffffffffdbffL & l) != 0L)
                     jjCheckNAddStbtes(12, 14);
                  brebk;
               cbse 8:
                  if ((0x2400L & l) != 0L && kind > 6)
                     kind = 6;
                  brebk;
               cbse 9:
                  if (curChbr == 10 && kind > 6)
                     kind = 6;
                  brebk;
               cbse 10:
                  if (curChbr == 13)
                     jjstbteSet[jjnewStbteCnt++] = 9;
                  brebk;
               cbse 11:
                  if ((0x3fe000000000000L & l) == 0L)
                     brebk;
                  if (kind > 24)
                     kind = 24;
                  jjCheckNAddTwoStbtes(12, 13);
                  brebk;
               cbse 12:
                  if ((0x3ff000000000000L & l) == 0L)
                     brebk;
                  if (kind > 24)
                     kind = 24;
                  jjCheckNAddTwoStbtes(12, 13);
                  brebk;
               cbse 14:
                  if (curChbr == 34)
                     jjCheckNAddTwoStbtes(15, 16);
                  brebk;
               cbse 15:
                  if ((0xfffffffbffffffffL & l) != 0L)
                     jjCheckNAddTwoStbtes(15, 16);
                  brebk;
               cbse 16:
                  if (curChbr == 34 && kind > 35)
                     kind = 35;
                  brebk;
               cbse 17:
                  if ((0x3ff000000000000L & l) == 0L)
                     brebk;
                  if (kind > 31)
                     kind = 31;
                  jjCheckNAddStbtes(15, 17);
                  brebk;
               cbse 18:
                  if ((0x3ff200000000000L & l) != 0L)
                     jjCheckNAddTwoStbtes(18, 19);
                  brebk;
               cbse 19:
                  if ((0x3ff000000000000L & l) == 0L)
                     brebk;
                  if (kind > 31)
                     kind = 31;
                  jjCheckNAdd(19);
                  brebk;
               cbse 20:
                  if ((0x3ff000000000000L & l) == 0L)
                     brebk;
                  if (kind > 31)
                     kind = 31;
                  jjCheckNAdd(20);
                  brebk;
               cbse 21:
                  if ((0x3ff000000000000L & l) == 0L)
                     brebk;
                  if (kind > 31)
                     kind = 31;
                  jjCheckNAddStbtes(0, 2);
                  brebk;
               cbse 22:
                  if ((0x3ff000000000000L & l) != 0L)
                     jjCheckNAddStbtes(6, 9);
                  brebk;
               cbse 23:
                  if ((0x3ff000000000000L & l) != 0L)
                     jjCheckNAddTwoStbtes(23, 24);
                  brebk;
               cbse 24:
                  if (curChbr == 58)
                     jjCheckNAddTwoStbtes(23, 25);
                  brebk;
               cbse 25:
               cbse 41:
                  if (curChbr == 58 && kind > 28)
                     kind = 28;
                  brebk;
               cbse 26:
                  if ((0x3ff000000000000L & l) != 0L)
                     jjCheckNAddTwoStbtes(26, 27);
                  brebk;
               cbse 27:
                  if (curChbr == 58)
                     jjCheckNAddStbtes(3, 5);
                  brebk;
               cbse 28:
               cbse 42:
                  if (curChbr == 58)
                     jjCheckNAddTwoStbtes(29, 36);
                  brebk;
               cbse 29:
                  if ((0x3ff000000000000L & l) != 0L)
                     jjCheckNAddTwoStbtes(29, 30);
                  brebk;
               cbse 30:
                  if (curChbr == 46)
                     jjCheckNAdd(31);
                  brebk;
               cbse 31:
                  if ((0x3ff000000000000L & l) != 0L)
                     jjCheckNAddTwoStbtes(31, 32);
                  brebk;
               cbse 32:
                  if (curChbr == 46)
                     jjCheckNAdd(33);
                  brebk;
               cbse 33:
                  if ((0x3ff000000000000L & l) != 0L)
                     jjCheckNAddTwoStbtes(33, 34);
                  brebk;
               cbse 34:
                  if (curChbr == 46)
                     jjCheckNAdd(35);
                  brebk;
               cbse 35:
                  if ((0x3ff000000000000L & l) == 0L)
                     brebk;
                  if (kind > 28)
                     kind = 28;
                  jjCheckNAdd(35);
                  brebk;
               cbse 36:
                  if ((0x3ff000000000000L & l) == 0L)
                     brebk;
                  if (kind > 28)
                     kind = 28;
                  jjCheckNAddStbtes(24, 26);
                  brebk;
               cbse 37:
                  if ((0x3ff000000000000L & l) != 0L)
                     jjCheckNAddTwoStbtes(37, 28);
                  brebk;
               cbse 38:
                  if ((0x3ff000000000000L & l) == 0L)
                     brebk;
                  if (kind > 28)
                     kind = 28;
                  jjCheckNAdd(38);
                  brebk;
               cbse 39:
                  if ((0x3ff000000000000L & l) == 0L)
                     brebk;
                  if (kind > 28)
                     kind = 28;
                  jjCheckNAddStbtes(27, 31);
                  brebk;
               cbse 40:
                  if (curChbr == 58)
                     jjAddStbtes(10, 11);
                  brebk;
               cbse 43:
                  if (curChbr != 48)
                     brebk;
                  if (kind > 24)
                     kind = 24;
                  jjCheckNAddStbtes(18, 20);
                  brebk;
               cbse 45:
                  if ((0x3ff000000000000L & l) == 0L)
                     brebk;
                  if (kind > 24)
                     kind = 24;
                  jjCheckNAddTwoStbtes(45, 13);
                  brebk;
               cbse 46:
                  if ((0xff000000000000L & l) == 0L)
                     brebk;
                  if (kind > 24)
                     kind = 24;
                  jjCheckNAddTwoStbtes(46, 13);
                  brebk;
               defbult : brebk;
            }
         } while(i != stbrtsAt);
      }
      else if (curChbr < 128)
      {
         long l = 1L << (curChbr & 077);
         MbtchLoop: do
         {
            switch(jjstbteSet[--i])
            {
               cbse 49:
                  if ((0x7fffffe87fffffeL & l) != 0L)
                     jjCheckNAddTwoStbtes(18, 19);
                  if ((0x7fffffe07fffffeL & l) != 0L)
                  {
                     if (kind > 31)
                        kind = 31;
                     jjCheckNAddStbtes(0, 2);
                  }
                  if ((0x7fffffe07fffffeL & l) != 0L)
                  {
                     if (kind > 31)
                        kind = 31;
                     jjCheckNAdd(20);
                  }
                  if ((0x7fffffe07fffffeL & l) != 0L)
                  {
                     if (kind > 31)
                        kind = 31;
                     jjCheckNAdd(19);
                  }
                  brebk;
               cbse 48:
                  if ((0x7fffffe87fffffeL & l) != 0L)
                     jjCheckNAddTwoStbtes(18, 19);
                  if ((0x7fffffe07fffffeL & l) != 0L)
                  {
                     if (kind > 31)
                        kind = 31;
                     jjCheckNAddStbtes(0, 2);
                  }
                  if ((0x7fffffe07fffffeL & l) != 0L)
                  {
                     if (kind > 31)
                        kind = 31;
                     jjCheckNAdd(20);
                  }
                  if ((0x7e0000007eL & l) != 0L)
                     jjCheckNAddTwoStbtes(26, 27);
                  if ((0x7e0000007eL & l) != 0L)
                     jjCheckNAddTwoStbtes(23, 24);
                  brebk;
               cbse 47:
                  if ((0x7fffffe87fffffeL & l) != 0L)
                     jjCheckNAddTwoStbtes(18, 19);
                  if ((0x7fffffe07fffffeL & l) != 0L)
                  {
                     if (kind > 31)
                        kind = 31;
                     jjCheckNAddStbtes(0, 2);
                  }
                  if ((0x7fffffe07fffffeL & l) != 0L)
                  {
                     if (kind > 31)
                        kind = 31;
                     jjCheckNAdd(20);
                  }
                  brebk;
               cbse 50:
                  if ((0x7fffffe87fffffeL & l) != 0L)
                     jjCheckNAddTwoStbtes(18, 19);
                  if ((0x7fffffe07fffffeL & l) != 0L)
                  {
                     if (kind > 31)
                        kind = 31;
                     jjCheckNAddStbtes(0, 2);
                  }
                  if ((0x7fffffe07fffffeL & l) != 0L)
                  {
                     if (kind > 31)
                        kind = 31;
                     jjCheckNAdd(20);
                  }
                  if ((0x7fffffe07fffffeL & l) != 0L)
                  {
                     if (kind > 31)
                        kind = 31;
                     jjCheckNAdd(19);
                  }
                  if ((0x7e0000007eL & l) != 0L)
                     jjCheckNAddTwoStbtes(26, 27);
                  if ((0x7e0000007eL & l) != 0L)
                     jjCheckNAddTwoStbtes(23, 24);
                  brebk;
               cbse 5:
                  if ((0x7fffffe07fffffeL & l) != 0L)
                  {
                     if (kind > 31)
                        kind = 31;
                     jjCheckNAddStbtes(15, 17);
                  }
                  if ((0x7e0000007eL & l) != 0L)
                     jjCheckNAddStbtes(6, 9);
                  brebk;
               cbse 51:
                  if ((0x7fffffe87fffffeL & l) != 0L)
                     jjCheckNAddTwoStbtes(18, 19);
                  if ((0x7fffffe07fffffeL & l) != 0L)
                  {
                     if (kind > 31)
                        kind = 31;
                     jjCheckNAdd(19);
                  }
                  brebk;
               cbse 1:
                  jjAddStbtes(21, 23);
                  brebk;
               cbse 7:
                  jjAddStbtes(12, 14);
                  brebk;
               cbse 13:
                  if ((0x100000001000L & l) != 0L && kind > 24)
                     kind = 24;
                  brebk;
               cbse 15:
                  jjAddStbtes(32, 33);
                  brebk;
               cbse 17:
                  if ((0x7fffffe07fffffeL & l) == 0L)
                     brebk;
                  if (kind > 31)
                     kind = 31;
                  jjCheckNAddStbtes(15, 17);
                  brebk;
               cbse 18:
                  if ((0x7fffffe87fffffeL & l) != 0L)
                     jjCheckNAddTwoStbtes(18, 19);
                  brebk;
               cbse 19:
                  if ((0x7fffffe07fffffeL & l) == 0L)
                     brebk;
                  if (kind > 31)
                     kind = 31;
                  jjCheckNAdd(19);
                  brebk;
               cbse 20:
                  if ((0x7fffffe07fffffeL & l) == 0L)
                     brebk;
                  if (kind > 31)
                     kind = 31;
                  jjCheckNAdd(20);
                  brebk;
               cbse 21:
                  if ((0x7fffffe07fffffeL & l) == 0L)
                     brebk;
                  if (kind > 31)
                     kind = 31;
                  jjCheckNAddStbtes(0, 2);
                  brebk;
               cbse 22:
                  if ((0x7e0000007eL & l) != 0L)
                     jjCheckNAddStbtes(6, 9);
                  brebk;
               cbse 23:
                  if ((0x7e0000007eL & l) != 0L)
                     jjCheckNAddTwoStbtes(23, 24);
                  brebk;
               cbse 26:
                  if ((0x7e0000007eL & l) != 0L)
                     jjCheckNAddTwoStbtes(26, 27);
                  brebk;
               cbse 36:
                  if ((0x7e0000007eL & l) == 0L)
                     brebk;
                  if (kind > 28)
                     kind = 28;
                  jjCheckNAddStbtes(24, 26);
                  brebk;
               cbse 37:
                  if ((0x7e0000007eL & l) != 0L)
                     jjCheckNAddTwoStbtes(37, 28);
                  brebk;
               cbse 38:
                  if ((0x7e0000007eL & l) == 0L)
                     brebk;
                  if (kind > 28)
                     kind = 28;
                  jjCheckNAdd(38);
                  brebk;
               cbse 39:
                  if ((0x7e0000007eL & l) == 0L)
                     brebk;
                  if (kind > 28)
                     kind = 28;
                  jjCheckNAddStbtes(27, 31);
                  brebk;
               cbse 44:
                  if ((0x100000001000000L & l) != 0L)
                     jjCheckNAdd(45);
                  brebk;
               cbse 45:
                  if ((0x7e0000007eL & l) == 0L)
                     brebk;
                  if (kind > 24)
                     kind = 24;
                  jjCheckNAddTwoStbtes(45, 13);
                  brebk;
               defbult : brebk;
            }
         } while(i != stbrtsAt);
      }
      else
      {
         int i2 = (curChbr & 0xff) >> 6;
         long l2 = 1L << (curChbr & 077);
         MbtchLoop: do
         {
            switch(jjstbteSet[--i])
            {
               cbse 1:
                  if ((jjbitVec0[i2] & l2) != 0L)
                     jjAddStbtes(21, 23);
                  brebk;
               cbse 7:
                  if ((jjbitVec0[i2] & l2) != 0L)
                     jjAddStbtes(12, 14);
                  brebk;
               cbse 15:
                  if ((jjbitVec0[i2] & l2) != 0L)
                     jjAddStbtes(32, 33);
                  brebk;
               defbult : brebk;
            }
         } while(i != stbrtsAt);
      }
      if (kind != 0x7fffffff)
      {
         jjmbtchedKind = kind;
         jjmbtchedPos = curPos;
         kind = 0x7fffffff;
      }
      ++curPos;
      if ((i = jjnewStbteCnt) == (stbrtsAt = 47 - (jjnewStbteCnt = stbrtsAt)))
         return curPos;
      try { curChbr = input_strebm.rebdChbr(); }
      cbtch(jbvb.io.IOException e) { return curPos; }
   }
}
stbtic finbl int[] jjnextStbtes = {
   18, 19, 21, 28, 29, 39, 23, 24, 26, 27, 41, 42, 7, 8, 10, 18,
   20, 21, 44, 46, 13, 1, 2, 4, 37, 28, 38, 26, 27, 37, 28, 38,
   15, 16,
};
public stbtic finbl String[] jjstrLiterblImbges = {
"", null, null, null, null, null, null, "\141\143\143\145\163\163",
"\141\143\154", "\75", "\143\157\155\155\165\156\151\164\151\145\163",
"\145\156\164\145\162\160\162\151\163\145", "\150\157\163\164\163", "\173", "\155\141\156\141\147\145\162\163", "\55",
"\175", "\162\145\141\144\55\157\156\154\171",
"\162\145\141\144\55\167\162\151\164\145", "\164\162\141\160", "\151\156\146\157\162\155",
"\164\162\141\160\55\143\157\155\155\165\156\151\164\171", "\151\156\146\157\162\155\55\143\157\155\155\165\156\151\164\171",
"\164\162\141\160\55\156\165\155", null, null, null, null, null, null, null, null, null, null, null, null, "\54",
"\56", "\41", "\57", };
public stbtic finbl String[] lexStbteNbmes = {
   "DEFAULT",
};
stbtic finbl long[] jjtoToken = {
   0xf891ffff81L,
};
stbtic finbl long[] jjtoSkip = {
   0x7eL,
};
privbte ASCII_ChbrStrebm input_strebm;
privbte finbl int[] jjrounds = new int[47];
privbte finbl int[] jjstbteSet = new int[94];
protected chbr curChbr;
public PbrserTokenMbnbger(ASCII_ChbrStrebm strebm)
{
   if (ASCII_ChbrStrebm.stbticFlbg)
      throw new Error("ERROR: Cbnnot use b stbtic ChbrStrebm clbss with b non-stbtic lexicbl bnblyzer.");
   input_strebm = strebm;
}
public PbrserTokenMbnbger(ASCII_ChbrStrebm strebm, int lexStbte)
{
   this(strebm);
   SwitchTo(lexStbte);
}
public void ReInit(ASCII_ChbrStrebm strebm)
{
   jjmbtchedPos = jjnewStbteCnt = 0;
   curLexStbte = defbultLexStbte;
   input_strebm = strebm;
   ReInitRounds();
}
privbte finbl void ReInitRounds()
{
   int i;
   jjround = 0x80000001;
   for (i = 47; i-- > 0;)
      jjrounds[i] = 0x80000000;
}
public void ReInit(ASCII_ChbrStrebm strebm, int lexStbte)
{
   ReInit(strebm);
   SwitchTo(lexStbte);
}
public void SwitchTo(int lexStbte)
{
   if (lexStbte >= 1 || lexStbte < 0)
      throw new TokenMgrError("Error: Ignoring invblid lexicbl stbte : " + lexStbte + ". Stbte unchbnged.", TokenMgrError.INVALID_LEXICAL_STATE);
   else
      curLexStbte = lexStbte;
}

privbte finbl Token jjFillToken()
{
   Token t = Token.newToken(jjmbtchedKind);
   t.kind = jjmbtchedKind;
   String im = jjstrLiterblImbges[jjmbtchedKind];
   t.imbge = (im == null) ? input_strebm.GetImbge() : im;
   t.beginLine = input_strebm.getBeginLine();
   t.beginColumn = input_strebm.getBeginColumn();
   t.endLine = input_strebm.getEndLine();
   t.endColumn = input_strebm.getEndColumn();
   return t;
}

int curLexStbte = 0;
int defbultLexStbte = 0;
int jjnewStbteCnt;
int jjround;
int jjmbtchedPos;
int jjmbtchedKind;

public finbl Token getNextToken()
{
  int kind;
  Token speciblToken = null;
  Token mbtchedToken;
  int curPos = 0;

  EOFLoop :
  for (;;)
  {
   try
   {
      curChbr = input_strebm.BeginToken();
   }
   cbtch(jbvb.io.IOException e)
   {
      jjmbtchedKind = 0;
      mbtchedToken = jjFillToken();
      return mbtchedToken;
   }

   try { input_strebm.bbckup(0);
      while (curChbr <= 32 && (0x100002600L & (1L << curChbr)) != 0L)
         curChbr = input_strebm.BeginToken();
   }
   cbtch (jbvb.io.IOException e1) { continue EOFLoop; }
   jjmbtchedKind = 0x7fffffff;
   jjmbtchedPos = 0;
   curPos = jjMoveStringLiterblDfb0_0();
   if (jjmbtchedKind != 0x7fffffff)
   {
      if (jjmbtchedPos + 1 < curPos)
         input_strebm.bbckup(curPos - jjmbtchedPos - 1);
      if ((jjtoToken[jjmbtchedKind >> 6] & (1L << (jjmbtchedKind & 077))) != 0L)
      {
         mbtchedToken = jjFillToken();
         return mbtchedToken;
      }
      else
      {
         continue EOFLoop;
      }
   }
   int error_line = input_strebm.getEndLine();
   int error_column = input_strebm.getEndColumn();
   String error_bfter = null;
   boolebn EOFSeen = fblse;
   try { input_strebm.rebdChbr(); input_strebm.bbckup(1); }
   cbtch (jbvb.io.IOException e1) {
      EOFSeen = true;
      error_bfter = curPos <= 1 ? "" : input_strebm.GetImbge();
      if (curChbr == '\n' || curChbr == '\r') {
         error_line++;
         error_column = 0;
      }
      else
         error_column++;
   }
   if (!EOFSeen) {
      input_strebm.bbckup(1);
      error_bfter = curPos <= 1 ? "" : input_strebm.GetImbge();
   }
   throw new TokenMgrError(EOFSeen, curLexStbte, error_line, error_column, error_bfter, curChbr, TokenMgrError.LEXICAL_ERROR);
  }
}

}
