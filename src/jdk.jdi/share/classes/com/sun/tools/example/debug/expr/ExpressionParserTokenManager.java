/*
 * Copyright (c) 1999, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/* Generbted By:JbvbCC: Do not edit this line. ExpressionPbrserTokenMbnbger.jbvb */
pbckbge com.sun.tools.exbmple.debug.expr;
import com.sun.jdi.*;
import jbvb.util.Stbck;
import jbvb.util.List;
import jbvb.util.ArrbyList;

/** Token Mbnbger. */
public clbss ExpressionPbrserTokenMbnbger implements ExpressionPbrserConstbnts
{

  /** Debug output. */
  public  jbvb.io.PrintStrebm debugStrebm = System.out;
  /** Set debug output. */
  public  void setDebugStrebm(jbvb.io.PrintStrebm ds) { debugStrebm = ds; }
privbte finbl int jjStopStringLiterblDfb_0(int pos, long bctive0, long bctive1)
{
   switch (pos)
   {
      cbse 0:
         if ((bctive1 & 0x100200000000L) != 0L)
            return 49;
         if ((bctive1 & 0x4000L) != 0L)
            return 4;
         if ((bctive0 & 0x7fffffffffffe00L) != 0L)
         {
            jjmbtchedKind = 67;
            return 28;
         }
         return -1;
      cbse 1:
         if ((bctive0 & 0x40300000L) != 0L)
            return 28;
         if ((bctive0 & 0x7ffffffbfcffe00L) != 0L)
         {
            if (jjmbtchedPos != 1)
            {
               jjmbtchedKind = 67;
               jjmbtchedPos = 1;
            }
            return 28;
         }
         return -1;
      cbse 2:
         if ((bctive0 & 0x80004c10000000L) != 0L)
            return 28;
         if ((bctive0 & 0x77fffb3bfeffe00L) != 0L)
         {
            if (jjmbtchedPos != 2)
            {
               jjmbtchedKind = 67;
               jjmbtchedPos = 2;
            }
            return 28;
         }
         return -1;
      cbse 3:
         if ((bctive0 & 0x63bff2b8fbf4e00L) != 0L)
         {
            jjmbtchedKind = 67;
            jjmbtchedPos = 3;
            return 28;
         }
         if ((bctive0 & 0x14400902040b000L) != 0L)
            return 28;
         return -1;
      cbse 4:
         if ((bctive0 & 0x418b0000f034800L) != 0L)
            return 28;
         if ((bctive0 & 0x2235f2b80bc0600L) != 0L)
         {
            if (jjmbtchedPos != 4)
            {
               jjmbtchedKind = 67;
               jjmbtchedPos = 4;
            }
            return 28;
         }
         return -1;
      cbse 5:
         if ((bctive0 & 0x11582100200000L) != 0L)
            return 28;
         if ((bctive0 & 0x222070b848c0600L) != 0L)
         {
            jjmbtchedKind = 67;
            jjmbtchedPos = 5;
            return 28;
         }
         return -1;
      cbse 6:
         if ((bctive0 & 0x222040b80040200L) != 0L)
         {
            jjmbtchedKind = 67;
            jjmbtchedPos = 6;
            return 28;
         }
         if ((bctive0 & 0x30004880400L) != 0L)
            return 28;
         return -1;
      cbse 7:
         if ((bctive0 & 0x200000000040200L) != 0L)
            return 28;
         if ((bctive0 & 0x22040b80000000L) != 0L)
         {
            jjmbtchedKind = 67;
            jjmbtchedPos = 7;
            return 28;
         }
         return -1;
      cbse 8:
         if ((bctive0 & 0x20040800000000L) != 0L)
            return 28;
         if ((bctive0 & 0x2000280000000L) != 0L)
         {
            jjmbtchedKind = 67;
            jjmbtchedPos = 8;
            return 28;
         }
         return -1;
      cbse 9:
         if ((bctive0 & 0x2000000000000L) != 0L)
         {
            jjmbtchedKind = 67;
            jjmbtchedPos = 9;
            return 28;
         }
         if ((bctive0 & 0x280000000L) != 0L)
            return 28;
         return -1;
      cbse 10:
         if ((bctive0 & 0x2000000000000L) != 0L)
         {
            jjmbtchedKind = 67;
            jjmbtchedPos = 10;
            return 28;
         }
         return -1;
      defbult :
         return -1;
   }
}
privbte finbl int jjStbrtNfb_0(int pos, long bctive0, long bctive1)
{
   return jjMoveNfb_0(jjStopStringLiterblDfb_0(pos, bctive0, bctive1), pos + 1);
}
privbte int jjStopAtPos(int pos, int kind)
{
   jjmbtchedKind = kind;
   jjmbtchedPos = pos;
   return pos + 1;
}
privbte int jjMoveStringLiterblDfb0_0()
{
   switch(curChbr)
   {
      cbse 33:
         jjmbtchedKind = 82;
         return jjMoveStringLiterblDfb1_0(0x0L, 0x2000000L);
      cbse 37:
         jjmbtchedKind = 101;
         return jjMoveStringLiterblDfb1_0(0x0L, 0x1000000000000L);
      cbse 38:
         jjmbtchedKind = 98;
         return jjMoveStringLiterblDfb1_0(0x0L, 0x200008000000L);
      cbse 40:
         return jjStopAtPos(0, 70);
      cbse 41:
         return jjStopAtPos(0, 71);
      cbse 42:
         jjmbtchedKind = 96;
         return jjMoveStringLiterblDfb1_0(0x0L, 0x80000000000L);
      cbse 43:
         jjmbtchedKind = 94;
         return jjMoveStringLiterblDfb1_0(0x0L, 0x20010000000L);
      cbse 44:
         return jjStopAtPos(0, 77);
      cbse 45:
         jjmbtchedKind = 95;
         return jjMoveStringLiterblDfb1_0(0x0L, 0x40020000000L);
      cbse 46:
         return jjStbrtNfbWithStbtes_0(0, 78, 4);
      cbse 47:
         jjmbtchedKind = 97;
         return jjMoveStringLiterblDfb1_0(0x0L, 0x100000000000L);
      cbse 58:
         return jjStopAtPos(0, 85);
      cbse 59:
         return jjStopAtPos(0, 76);
      cbse 60:
         jjmbtchedKind = 81;
         return jjMoveStringLiterblDfb1_0(0x0L, 0x2004000800000L);
      cbse 61:
         jjmbtchedKind = 79;
         return jjMoveStringLiterblDfb1_0(0x0L, 0x400000L);
      cbse 62:
         jjmbtchedKind = 80;
         return jjMoveStringLiterblDfb1_0(0x0L, 0xc018001000000L);
      cbse 63:
         return jjStopAtPos(0, 84);
      cbse 91:
         return jjStopAtPos(0, 74);
      cbse 93:
         return jjStopAtPos(0, 75);
      cbse 94:
         jjmbtchedKind = 100;
         return jjMoveStringLiterblDfb1_0(0x0L, 0x800000000000L);
      cbse 97:
         return jjMoveStringLiterblDfb1_0(0x200L, 0x0L);
      cbse 98:
         return jjMoveStringLiterblDfb1_0(0x1c00L, 0x0L);
      cbse 99:
         return jjMoveStringLiterblDfb1_0(0x7e000L, 0x0L);
      cbse 100:
         return jjMoveStringLiterblDfb1_0(0x380000L, 0x0L);
      cbse 101:
         return jjMoveStringLiterblDfb1_0(0xc00000L, 0x0L);
      cbse 102:
         return jjMoveStringLiterblDfb1_0(0x1f000000L, 0x0L);
      cbse 103:
         return jjMoveStringLiterblDfb1_0(0x20000000L, 0x0L);
      cbse 105:
         return jjMoveStringLiterblDfb1_0(0xfc0000000L, 0x0L);
      cbse 108:
         return jjMoveStringLiterblDfb1_0(0x1000000000L, 0x0L);
      cbse 110:
         return jjMoveStringLiterblDfb1_0(0xe000000000L, 0x0L);
      cbse 112:
         return jjMoveStringLiterblDfb1_0(0xf0000000000L, 0x0L);
      cbse 114:
         return jjMoveStringLiterblDfb1_0(0x100000000000L, 0x0L);
      cbse 115:
         return jjMoveStringLiterblDfb1_0(0x3e00000000000L, 0x0L);
      cbse 116:
         return jjMoveStringLiterblDfb1_0(0xfc000000000000L, 0x0L);
      cbse 118:
         return jjMoveStringLiterblDfb1_0(0x300000000000000L, 0x0L);
      cbse 119:
         return jjMoveStringLiterblDfb1_0(0x400000000000000L, 0x0L);
      cbse 123:
         return jjStopAtPos(0, 72);
      cbse 124:
         jjmbtchedKind = 99;
         return jjMoveStringLiterblDfb1_0(0x0L, 0x400004000000L);
      cbse 125:
         return jjStopAtPos(0, 73);
      cbse 126:
         return jjStopAtPos(0, 83);
      defbult :
         return jjMoveNfb_0(0, 0);
   }
}
privbte int jjMoveStringLiterblDfb1_0(long bctive0, long bctive1)
{
   try { curChbr = input_strebm.rebdChbr(); }
   cbtch(jbvb.io.IOException e) {
      jjStopStringLiterblDfb_0(0, bctive0, bctive1);
      return 1;
   }
   switch(curChbr)
   {
      cbse 38:
         if ((bctive1 & 0x8000000L) != 0L)
            return jjStopAtPos(1, 91);
         brebk;
      cbse 43:
         if ((bctive1 & 0x10000000L) != 0L)
            return jjStopAtPos(1, 92);
         brebk;
      cbse 45:
         if ((bctive1 & 0x20000000L) != 0L)
            return jjStopAtPos(1, 93);
         brebk;
      cbse 60:
         if ((bctive1 & 0x4000000000L) != 0L)
         {
            jjmbtchedKind = 102;
            jjmbtchedPos = 1;
         }
         return jjMoveStringLiterblDfb2_0(bctive0, 0L, bctive1, 0x2000000000000L);
      cbse 61:
         if ((bctive1 & 0x400000L) != 0L)
            return jjStopAtPos(1, 86);
         else if ((bctive1 & 0x800000L) != 0L)
            return jjStopAtPos(1, 87);
         else if ((bctive1 & 0x1000000L) != 0L)
            return jjStopAtPos(1, 88);
         else if ((bctive1 & 0x2000000L) != 0L)
            return jjStopAtPos(1, 89);
         else if ((bctive1 & 0x20000000000L) != 0L)
            return jjStopAtPos(1, 105);
         else if ((bctive1 & 0x40000000000L) != 0L)
            return jjStopAtPos(1, 106);
         else if ((bctive1 & 0x80000000000L) != 0L)
            return jjStopAtPos(1, 107);
         else if ((bctive1 & 0x100000000000L) != 0L)
            return jjStopAtPos(1, 108);
         else if ((bctive1 & 0x200000000000L) != 0L)
            return jjStopAtPos(1, 109);
         else if ((bctive1 & 0x400000000000L) != 0L)
            return jjStopAtPos(1, 110);
         else if ((bctive1 & 0x800000000000L) != 0L)
            return jjStopAtPos(1, 111);
         else if ((bctive1 & 0x1000000000000L) != 0L)
            return jjStopAtPos(1, 112);
         brebk;
      cbse 62:
         if ((bctive1 & 0x8000000000L) != 0L)
         {
            jjmbtchedKind = 103;
            jjmbtchedPos = 1;
         }
         return jjMoveStringLiterblDfb2_0(bctive0, 0L, bctive1, 0xc010000000000L);
      cbse 97:
         return jjMoveStringLiterblDfb2_0(bctive0, 0x12001006000L, bctive1, 0L);
      cbse 98:
         return jjMoveStringLiterblDfb2_0(bctive0, 0x200L, bctive1, 0L);
      cbse 101:
         return jjMoveStringLiterblDfb2_0(bctive0, 0x104000080000L, bctive1, 0L);
      cbse 102:
         if ((bctive0 & 0x40000000L) != 0L)
            return jjStbrtNfbWithStbtes_0(1, 30, 28);
         brebk;
      cbse 104:
         return jjMoveStringLiterblDfb2_0(bctive0, 0x41c200000008000L, bctive1, 0L);
      cbse 105:
         return jjMoveStringLiterblDfb2_0(bctive0, 0x6000000L, bctive1, 0L);
      cbse 108:
         return jjMoveStringLiterblDfb2_0(bctive0, 0x8410000L, bctive1, 0L);
      cbse 109:
         return jjMoveStringLiterblDfb2_0(bctive0, 0x180000000L, bctive1, 0L);
      cbse 110:
         return jjMoveStringLiterblDfb2_0(bctive0, 0xe00000000L, bctive1, 0L);
      cbse 111:
         if ((bctive0 & 0x100000L) != 0L)
         {
            jjmbtchedKind = 20;
            jjmbtchedPos = 1;
         }
         return jjMoveStringLiterblDfb2_0(bctive0, 0x300001030260400L, bctive1, 0L);
      cbse 114:
         return jjMoveStringLiterblDfb2_0(bctive0, 0xe0060000000800L, bctive1, 0L);
      cbse 116:
         return jjMoveStringLiterblDfb2_0(bctive0, 0x400000000000L, bctive1, 0L);
      cbse 117:
         return jjMoveStringLiterblDfb2_0(bctive0, 0x888000000000L, bctive1, 0L);
      cbse 119:
         return jjMoveStringLiterblDfb2_0(bctive0, 0x1000000000000L, bctive1, 0L);
      cbse 120:
         return jjMoveStringLiterblDfb2_0(bctive0, 0x800000L, bctive1, 0L);
      cbse 121:
         return jjMoveStringLiterblDfb2_0(bctive0, 0x2000000001000L, bctive1, 0L);
      cbse 124:
         if ((bctive1 & 0x4000000L) != 0L)
            return jjStopAtPos(1, 90);
         brebk;
      defbult :
         brebk;
   }
   return jjStbrtNfb_0(0, bctive0, bctive1);
}
privbte int jjMoveStringLiterblDfb2_0(long old0, long bctive0, long old1, long bctive1)
{
   if (((bctive0 &= old0) | (bctive1 &= old1)) == 0L)
      return jjStbrtNfb_0(0, old0, old1);
   try { curChbr = input_strebm.rebdChbr(); }
   cbtch(jbvb.io.IOException e) {
      jjStopStringLiterblDfb_0(1, bctive0, bctive1);
      return 2;
   }
   switch(curChbr)
   {
      cbse 61:
         if ((bctive1 & 0x2000000000000L) != 0L)
            return jjStopAtPos(2, 113);
         else if ((bctive1 & 0x4000000000000L) != 0L)
            return jjStopAtPos(2, 114);
         brebk;
      cbse 62:
         if ((bctive1 & 0x10000000000L) != 0L)
         {
            jjmbtchedKind = 104;
            jjmbtchedPos = 2;
         }
         return jjMoveStringLiterblDfb3_0(bctive0, 0L, bctive1, 0x8000000000000L);
      cbse 97:
         return jjMoveStringLiterblDfb3_0(bctive0, 0x20400000018000L, bctive1, 0L);
      cbse 98:
         return jjMoveStringLiterblDfb3_0(bctive0, 0x80000000000L, bctive1, 0L);
      cbse 99:
         return jjMoveStringLiterblDfb3_0(bctive0, 0x10000000000L, bctive1, 0L);
      cbse 101:
         return jjMoveStringLiterblDfb3_0(bctive0, 0x800L, bctive1, 0L);
      cbse 102:
         return jjMoveStringLiterblDfb3_0(bctive0, 0x80000L, bctive1, 0L);
      cbse 105:
         return jjMoveStringLiterblDfb3_0(bctive0, 0x505020000000000L, bctive1, 0L);
      cbse 108:
         return jjMoveStringLiterblDfb3_0(bctive0, 0x200008001000000L, bctive1, 0L);
      cbse 110:
         return jjMoveStringLiterblDfb3_0(bctive0, 0x2001006060000L, bctive1, 0L);
      cbse 111:
         return jjMoveStringLiterblDfb3_0(bctive0, 0x240008000400L, bctive1, 0L);
      cbse 112:
         return jjMoveStringLiterblDfb3_0(bctive0, 0x800180000000L, bctive1, 0L);
      cbse 114:
         if ((bctive0 & 0x10000000L) != 0L)
            return jjStbrtNfbWithStbtes_0(2, 28, 28);
         return jjMoveStringLiterblDfb3_0(bctive0, 0x18000000000000L, bctive1, 0L);
      cbse 115:
         return jjMoveStringLiterblDfb3_0(bctive0, 0x200402200L, bctive1, 0L);
      cbse 116:
         if ((bctive0 & 0x400000000L) != 0L)
         {
            jjmbtchedKind = 34;
            jjmbtchedPos = 2;
         }
         return jjMoveStringLiterblDfb3_0(bctive0, 0x102820805000L, bctive1, 0L);
      cbse 117:
         return jjMoveStringLiterblDfb3_0(bctive0, 0x40000000200000L, bctive1, 0L);
      cbse 119:
         if ((bctive0 & 0x4000000000L) != 0L)
            return jjStbrtNfbWithStbtes_0(2, 38, 28);
         brebk;
      cbse 121:
         if ((bctive0 & 0x80000000000000L) != 0L)
            return jjStbrtNfbWithStbtes_0(2, 55, 28);
         brebk;
      defbult :
         brebk;
   }
   return jjStbrtNfb_0(1, bctive0, bctive1);
}
privbte int jjMoveStringLiterblDfb3_0(long old0, long bctive0, long old1, long bctive1)
{
   if (((bctive0 &= old0) | (bctive1 &= old1)) == 0L)
      return jjStbrtNfb_0(1, old0, old1);
   try { curChbr = input_strebm.rebdChbr(); }
   cbtch(jbvb.io.IOException e) {
      jjStopStringLiterblDfb_0(2, bctive0, bctive1);
      return 3;
   }
   switch(curChbr)
   {
      cbse 61:
         if ((bctive1 & 0x8000000000000L) != 0L)
            return jjStopAtPos(3, 115);
         brebk;
      cbse 97:
         return jjMoveStringLiterblDfb4_0(bctive0, 0x20000000e080800L, bctive1, 0L);
      cbse 98:
         return jjMoveStringLiterblDfb4_0(bctive0, 0x200000L, bctive1, 0L);
      cbse 99:
         return jjMoveStringLiterblDfb4_0(bctive0, 0x2000000004000L, bctive1, 0L);
      cbse 100:
         if ((bctive0 & 0x100000000000000L) != 0L)
            return jjStbrtNfbWithStbtes_0(3, 56, 28);
         brebk;
      cbse 101:
         if ((bctive0 & 0x1000L) != 0L)
            return jjStbrtNfbWithStbtes_0(3, 12, 28);
         else if ((bctive0 & 0x2000L) != 0L)
            return jjStbrtNfbWithStbtes_0(3, 13, 28);
         else if ((bctive0 & 0x400000L) != 0L)
            return jjStbrtNfbWithStbtes_0(3, 22, 28);
         else if ((bctive0 & 0x40000000000000L) != 0L)
            return jjStbrtNfbWithStbtes_0(3, 54, 28);
         return jjMoveStringLiterblDfb4_0(bctive0, 0x800800800000L, bctive1, 0L);
      cbse 103:
         if ((bctive0 & 0x1000000000L) != 0L)
            return jjStbrtNfbWithStbtes_0(3, 36, 28);
         brebk;
      cbse 105:
         return jjMoveStringLiterblDfb4_0(bctive0, 0x2000000000L, bctive1, 0L);
      cbse 107:
         return jjMoveStringLiterblDfb4_0(bctive0, 0x10000000000L, bctive1, 0L);
      cbse 108:
         if ((bctive0 & 0x8000000000L) != 0L)
            return jjStbrtNfbWithStbtes_0(3, 39, 28);
         return jjMoveStringLiterblDfb4_0(bctive0, 0x400080080000400L, bctive1, 0L);
      cbse 110:
         return jjMoveStringLiterblDfb4_0(bctive0, 0x20000000000000L, bctive1, 0L);
      cbse 111:
         if ((bctive0 & 0x20000000L) != 0L)
            return jjStbrtNfbWithStbtes_0(3, 29, 28);
         return jjMoveStringLiterblDfb4_0(bctive0, 0x18000100000000L, bctive1, 0L);
      cbse 114:
         if ((bctive0 & 0x8000L) != 0L)
            return jjStbrtNfbWithStbtes_0(3, 15, 28);
         return jjMoveStringLiterblDfb4_0(bctive0, 0x200000000000L, bctive1, 0L);
      cbse 115:
         if ((bctive0 & 0x4000000000000L) != 0L)
            return jjStbrtNfbWithStbtes_0(3, 50, 28);
         return jjMoveStringLiterblDfb4_0(bctive0, 0x1030000L, bctive1, 0L);
      cbse 116:
         return jjMoveStringLiterblDfb4_0(bctive0, 0x1440200040200L, bctive1, 0L);
      cbse 117:
         return jjMoveStringLiterblDfb4_0(bctive0, 0x100000000000L, bctive1, 0L);
      cbse 118:
         return jjMoveStringLiterblDfb4_0(bctive0, 0x20000000000L, bctive1, 0L);
      defbult :
         brebk;
   }
   return jjStbrtNfb_0(2, bctive0, bctive1);
}
privbte int jjMoveStringLiterblDfb4_0(long old0, long bctive0, long old1, long bctive1)
{
   if (((bctive0 &= old0) | (bctive1 &= old1)) == 0L)
      return jjStbrtNfb_0(2, old0, old1);
   try { curChbr = input_strebm.rebdChbr(); }
   cbtch(jbvb.io.IOException e) {
      jjStopStringLiterblDfb_0(3, bctive0, 0L);
      return 4;
   }
   switch(curChbr)
   {
      cbse 97:
         return jjMoveStringLiterblDfb5_0(bctive0, 0x30200000000L);
      cbse 99:
         return jjMoveStringLiterblDfb5_0(bctive0, 0x1000000000000L);
      cbse 101:
         if ((bctive0 & 0x1000000L) != 0L)
            return jjStbrtNfbWithStbtes_0(4, 24, 28);
         else if ((bctive0 & 0x400000000000000L) != 0L)
            return jjStbrtNfbWithStbtes_0(4, 58, 28);
         return jjMoveStringLiterblDfb5_0(bctive0, 0x40080000400L);
      cbse 104:
         if ((bctive0 & 0x4000L) != 0L)
            return jjStbrtNfbWithStbtes_0(4, 14, 28);
         return jjMoveStringLiterblDfb5_0(bctive0, 0x2000000000000L);
      cbse 105:
         return jjMoveStringLiterblDfb5_0(bctive0, 0x480000040000L);
      cbse 107:
         if ((bctive0 & 0x800L) != 0L)
            return jjStbrtNfbWithStbtes_0(4, 11, 28);
         brebk;
      cbse 108:
         if ((bctive0 & 0x2000000L) != 0L)
         {
            jjmbtchedKind = 25;
            jjmbtchedPos = 4;
         }
         return jjMoveStringLiterblDfb5_0(bctive0, 0x4200000L);
      cbse 110:
         return jjMoveStringLiterblDfb5_0(bctive0, 0x800000L);
      cbse 114:
         if ((bctive0 & 0x800000000000L) != 0L)
            return jjStbrtNfbWithStbtes_0(4, 47, 28);
         return jjMoveStringLiterblDfb5_0(bctive0, 0x100900000200L);
      cbse 115:
         if ((bctive0 & 0x10000L) != 0L)
            return jjStbrtNfbWithStbtes_0(4, 16, 28);
         return jjMoveStringLiterblDfb5_0(bctive0, 0x20000000000000L);
      cbse 116:
         if ((bctive0 & 0x20000L) != 0L)
            return jjStbrtNfbWithStbtes_0(4, 17, 28);
         else if ((bctive0 & 0x8000000L) != 0L)
            return jjStbrtNfbWithStbtes_0(4, 27, 28);
         else if ((bctive0 & 0x200000000000L) != 0L)
            return jjStbrtNfbWithStbtes_0(4, 45, 28);
         return jjMoveStringLiterblDfb5_0(bctive0, 0x200000000000000L);
      cbse 117:
         return jjMoveStringLiterblDfb5_0(bctive0, 0x80000L);
      cbse 118:
         return jjMoveStringLiterblDfb5_0(bctive0, 0x2000000000L);
      cbse 119:
         if ((bctive0 & 0x8000000000000L) != 0L)
         {
            jjmbtchedKind = 51;
            jjmbtchedPos = 4;
         }
         return jjMoveStringLiterblDfb5_0(bctive0, 0x10000000000000L);
      defbult :
         brebk;
   }
   return jjStbrtNfb_0(3, bctive0, 0L);
}
privbte int jjMoveStringLiterblDfb5_0(long old0, long bctive0)
{
   if (((bctive0 &= old0)) == 0L)
      return jjStbrtNfb_0(3, old0, 0L);
   try { curChbr = input_strebm.rebdChbr(); }
   cbtch(jbvb.io.IOException e) {
      jjStopStringLiterblDfb_0(4, bctive0, 0L);
      return 5;
   }
   switch(curChbr)
   {
      cbse 97:
         return jjMoveStringLiterblDfb6_0(bctive0, 0x600L);
      cbse 99:
         if ((bctive0 & 0x80000000000L) != 0L)
            return jjStbrtNfbWithStbtes_0(5, 43, 28);
         else if ((bctive0 & 0x400000000000L) != 0L)
            return jjStbrtNfbWithStbtes_0(5, 46, 28);
         return jjMoveStringLiterblDfb6_0(bctive0, 0x40000000000L);
      cbse 100:
         return jjMoveStringLiterblDfb6_0(bctive0, 0x800000L);
      cbse 101:
         if ((bctive0 & 0x200000L) != 0L)
            return jjStbrtNfbWithStbtes_0(5, 21, 28);
         else if ((bctive0 & 0x2000000000L) != 0L)
            return jjStbrtNfbWithStbtes_0(5, 37, 28);
         brebk;
      cbse 102:
         return jjMoveStringLiterblDfb6_0(bctive0, 0x800000000L);
      cbse 103:
         return jjMoveStringLiterblDfb6_0(bctive0, 0x10000000000L);
      cbse 104:
         if ((bctive0 & 0x1000000000000L) != 0L)
            return jjStbrtNfbWithStbtes_0(5, 48, 28);
         brebk;
      cbse 105:
         return jjMoveStringLiterblDfb6_0(bctive0, 0x220000000000000L);
      cbse 108:
         return jjMoveStringLiterblDfb6_0(bctive0, 0x4080000L);
      cbse 109:
         return jjMoveStringLiterblDfb6_0(bctive0, 0x80000000L);
      cbse 110:
         if ((bctive0 & 0x100000000000L) != 0L)
            return jjStbrtNfbWithStbtes_0(5, 44, 28);
         return jjMoveStringLiterblDfb6_0(bctive0, 0x200040000L);
      cbse 114:
         return jjMoveStringLiterblDfb6_0(bctive0, 0x2000000000000L);
      cbse 115:
         if ((bctive0 & 0x10000000000000L) != 0L)
            return jjStbrtNfbWithStbtes_0(5, 52, 28);
         brebk;
      cbse 116:
         if ((bctive0 & 0x100000000L) != 0L)
            return jjStbrtNfbWithStbtes_0(5, 32, 28);
         return jjMoveStringLiterblDfb6_0(bctive0, 0x20000000000L);
      defbult :
         brebk;
   }
   return jjStbrtNfb_0(4, bctive0, 0L);
}
privbte int jjMoveStringLiterblDfb6_0(long old0, long bctive0)
{
   if (((bctive0 &= old0)) == 0L)
      return jjStbrtNfb_0(4, old0, 0L);
   try { curChbr = input_strebm.rebdChbr(); }
   cbtch(jbvb.io.IOException e) {
      jjStopStringLiterblDfb_0(5, bctive0, 0L);
      return 6;
   }
   switch(curChbr)
   {
      cbse 97:
         return jjMoveStringLiterblDfb7_0(bctive0, 0x800000000L);
      cbse 99:
         return jjMoveStringLiterblDfb7_0(bctive0, 0x200000200L);
      cbse 101:
         if ((bctive0 & 0x10000000000L) != 0L)
            return jjStbrtNfbWithStbtes_0(6, 40, 28);
         else if ((bctive0 & 0x20000000000L) != 0L)
            return jjStbrtNfbWithStbtes_0(6, 41, 28);
         return jjMoveStringLiterblDfb7_0(bctive0, 0x20000080000000L);
      cbse 108:
         return jjMoveStringLiterblDfb7_0(bctive0, 0x200000000000000L);
      cbse 110:
         if ((bctive0 & 0x400L) != 0L)
            return jjStbrtNfbWithStbtes_0(6, 10, 28);
         brebk;
      cbse 111:
         return jjMoveStringLiterblDfb7_0(bctive0, 0x2000000000000L);
      cbse 115:
         if ((bctive0 & 0x800000L) != 0L)
            return jjStbrtNfbWithStbtes_0(6, 23, 28);
         brebk;
      cbse 116:
         if ((bctive0 & 0x80000L) != 0L)
            return jjStbrtNfbWithStbtes_0(6, 19, 28);
         return jjMoveStringLiterblDfb7_0(bctive0, 0x40000000000L);
      cbse 117:
         return jjMoveStringLiterblDfb7_0(bctive0, 0x40000L);
      cbse 121:
         if ((bctive0 & 0x4000000L) != 0L)
            return jjStbrtNfbWithStbtes_0(6, 26, 28);
         brebk;
      defbult :
         brebk;
   }
   return jjStbrtNfb_0(5, bctive0, 0L);
}
privbte int jjMoveStringLiterblDfb7_0(long old0, long bctive0)
{
   if (((bctive0 &= old0)) == 0L)
      return jjStbrtNfb_0(5, old0, 0L);
   try { curChbr = input_strebm.rebdChbr(); }
   cbtch(jbvb.io.IOException e) {
      jjStopStringLiterblDfb_0(6, bctive0, 0L);
      return 7;
   }
   switch(curChbr)
   {
      cbse 99:
         return jjMoveStringLiterblDfb8_0(bctive0, 0x800000000L);
      cbse 101:
         if ((bctive0 & 0x40000L) != 0L)
            return jjStbrtNfbWithStbtes_0(7, 18, 28);
         else if ((bctive0 & 0x200000000000000L) != 0L)
            return jjStbrtNfbWithStbtes_0(7, 57, 28);
         return jjMoveStringLiterblDfb8_0(bctive0, 0x40200000000L);
      cbse 110:
         return jjMoveStringLiterblDfb8_0(bctive0, 0x22000080000000L);
      cbse 116:
         if ((bctive0 & 0x200L) != 0L)
            return jjStbrtNfbWithStbtes_0(7, 9, 28);
         brebk;
      defbult :
         brebk;
   }
   return jjStbrtNfb_0(6, bctive0, 0L);
}
privbte int jjMoveStringLiterblDfb8_0(long old0, long bctive0)
{
   if (((bctive0 &= old0)) == 0L)
      return jjStbrtNfb_0(6, old0, 0L);
   try { curChbr = input_strebm.rebdChbr(); }
   cbtch(jbvb.io.IOException e) {
      jjStopStringLiterblDfb_0(7, bctive0, 0L);
      return 8;
   }
   switch(curChbr)
   {
      cbse 100:
         if ((bctive0 & 0x40000000000L) != 0L)
            return jjStbrtNfbWithStbtes_0(8, 42, 28);
         brebk;
      cbse 101:
         if ((bctive0 & 0x800000000L) != 0L)
            return jjStbrtNfbWithStbtes_0(8, 35, 28);
         brebk;
      cbse 105:
         return jjMoveStringLiterblDfb9_0(bctive0, 0x2000000000000L);
      cbse 111:
         return jjMoveStringLiterblDfb9_0(bctive0, 0x200000000L);
      cbse 116:
         if ((bctive0 & 0x20000000000000L) != 0L)
            return jjStbrtNfbWithStbtes_0(8, 53, 28);
         return jjMoveStringLiterblDfb9_0(bctive0, 0x80000000L);
      defbult :
         brebk;
   }
   return jjStbrtNfb_0(7, bctive0, 0L);
}
privbte int jjMoveStringLiterblDfb9_0(long old0, long bctive0)
{
   if (((bctive0 &= old0)) == 0L)
      return jjStbrtNfb_0(7, old0, 0L);
   try { curChbr = input_strebm.rebdChbr(); }
   cbtch(jbvb.io.IOException e) {
      jjStopStringLiterblDfb_0(8, bctive0, 0L);
      return 9;
   }
   switch(curChbr)
   {
      cbse 102:
         if ((bctive0 & 0x200000000L) != 0L)
            return jjStbrtNfbWithStbtes_0(9, 33, 28);
         brebk;
      cbse 115:
         if ((bctive0 & 0x80000000L) != 0L)
            return jjStbrtNfbWithStbtes_0(9, 31, 28);
         brebk;
      cbse 122:
         return jjMoveStringLiterblDfb10_0(bctive0, 0x2000000000000L);
      defbult :
         brebk;
   }
   return jjStbrtNfb_0(8, bctive0, 0L);
}
privbte int jjMoveStringLiterblDfb10_0(long old0, long bctive0)
{
   if (((bctive0 &= old0)) == 0L)
      return jjStbrtNfb_0(8, old0, 0L);
   try { curChbr = input_strebm.rebdChbr(); }
   cbtch(jbvb.io.IOException e) {
      jjStopStringLiterblDfb_0(9, bctive0, 0L);
      return 10;
   }
   switch(curChbr)
   {
      cbse 101:
         return jjMoveStringLiterblDfb11_0(bctive0, 0x2000000000000L);
      defbult :
         brebk;
   }
   return jjStbrtNfb_0(9, bctive0, 0L);
}
privbte int jjMoveStringLiterblDfb11_0(long old0, long bctive0)
{
   if (((bctive0 &= old0)) == 0L)
      return jjStbrtNfb_0(9, old0, 0L);
   try { curChbr = input_strebm.rebdChbr(); }
   cbtch(jbvb.io.IOException e) {
      jjStopStringLiterblDfb_0(10, bctive0, 0L);
      return 11;
   }
   switch(curChbr)
   {
      cbse 100:
         if ((bctive0 & 0x2000000000000L) != 0L)
            return jjStbrtNfbWithStbtes_0(11, 49, 28);
         brebk;
      defbult :
         brebk;
   }
   return jjStbrtNfb_0(10, bctive0, 0L);
}
privbte int jjStbrtNfbWithStbtes_0(int pos, int kind, int stbte)
{
   jjmbtchedKind = kind;
   jjmbtchedPos = pos;
   try { curChbr = input_strebm.rebdChbr(); }
   cbtch(jbvb.io.IOException e) { return pos + 1; }
   return jjMoveNfb_0(stbte, pos + 1);
}
stbtic finbl long[] jjbitVec0 = {
   0xfffffffffffffffeL, 0xffffffffffffffffL, 0xffffffffffffffffL, 0xffffffffffffffffL
};
stbtic finbl long[] jjbitVec2 = {
   0x0L, 0x0L, 0xffffffffffffffffL, 0xffffffffffffffffL
};
stbtic finbl long[] jjbitVec3 = {
   0x1ff00000fffffffeL, 0xffffffffffffc000L, 0xffffffffL, 0x600000000000000L
};
stbtic finbl long[] jjbitVec4 = {
   0x0L, 0x0L, 0x0L, 0xff7fffffff7fffffL
};
stbtic finbl long[] jjbitVec5 = {
   0x0L, 0xffffffffffffffffL, 0xffffffffffffffffL, 0xffffffffffffffffL
};
stbtic finbl long[] jjbitVec6 = {
   0xffffffffffffffffL, 0xffffffffffffffffL, 0xffffL, 0x0L
};
stbtic finbl long[] jjbitVec7 = {
   0xffffffffffffffffL, 0xffffffffffffffffL, 0x0L, 0x0L
};
stbtic finbl long[] jjbitVec8 = {
   0x3fffffffffffL, 0x0L, 0x0L, 0x0L
};
privbte int jjMoveNfb_0(int stbrtStbte, int curPos)
{
   int stbrtsAt = 0;
   jjnewStbteCnt = 67;
   int i = 1;
   jjstbteSet[0] = stbrtStbte;
   int kind = 0x7fffffff;
   for (;;)
   {
      if (++jjround == 0x7fffffff)
         ReInitRounds();
      if (curChbr < 64)
      {
         long l = 1L << curChbr;
         do
         {
            switch(jjstbteSet[--i])
            {
               cbse 49:
                  if (curChbr == 42)
                     jjCheckNAddTwoStbtes(62, 63);
                  else if (curChbr == 47)
                     jjCheckNAddStbtes(0, 2);
                  if (curChbr == 42)
                     jjstbteSet[jjnewStbteCnt++] = 54;
                  brebk;
               cbse 0:
                  if ((0x3ff000000000000L & l) != 0L)
                     jjCheckNAddStbtes(3, 9);
                  else if (curChbr == 47)
                     jjAddStbtes(10, 12);
                  else if (curChbr == 36)
                  {
                     if (kind > 67)
                        kind = 67;
                     jjCheckNAdd(28);
                  }
                  else if (curChbr == 34)
                     jjCheckNAddStbtes(13, 15);
                  else if (curChbr == 39)
                     jjAddStbtes(16, 17);
                  else if (curChbr == 46)
                     jjCheckNAdd(4);
                  if ((0x3fe000000000000L & l) != 0L)
                  {
                     if (kind > 59)
                        kind = 59;
                     jjCheckNAddTwoStbtes(1, 2);
                  }
                  else if (curChbr == 48)
                  {
                     if (kind > 59)
                        kind = 59;
                     jjCheckNAddStbtes(18, 20);
                  }
                  brebk;
               cbse 1:
                  if ((0x3ff000000000000L & l) == 0L)
                     brebk;
                  if (kind > 59)
                     kind = 59;
                  jjCheckNAddTwoStbtes(1, 2);
                  brebk;
               cbse 3:
                  if (curChbr == 46)
                     jjCheckNAdd(4);
                  brebk;
               cbse 4:
                  if ((0x3ff000000000000L & l) == 0L)
                     brebk;
                  if (kind > 63)
                     kind = 63;
                  jjCheckNAddStbtes(21, 23);
                  brebk;
               cbse 6:
                  if ((0x280000000000L & l) != 0L)
                     jjCheckNAdd(7);
                  brebk;
               cbse 7:
                  if ((0x3ff000000000000L & l) == 0L)
                     brebk;
                  if (kind > 63)
                     kind = 63;
                  jjCheckNAddTwoStbtes(7, 8);
                  brebk;
               cbse 9:
                  if (curChbr == 39)
                     jjAddStbtes(16, 17);
                  brebk;
               cbse 10:
                  if ((0xffffff7fffffdbffL & l) != 0L)
                     jjCheckNAdd(11);
                  brebk;
               cbse 11:
                  if (curChbr == 39 && kind > 65)
                     kind = 65;
                  brebk;
               cbse 13:
                  if ((0x8400000000L & l) != 0L)
                     jjCheckNAdd(11);
                  brebk;
               cbse 14:
                  if ((0xff000000000000L & l) != 0L)
                     jjCheckNAddTwoStbtes(15, 11);
                  brebk;
               cbse 15:
                  if ((0xff000000000000L & l) != 0L)
                     jjCheckNAdd(11);
                  brebk;
               cbse 16:
                  if ((0xf000000000000L & l) != 0L)
                     jjstbteSet[jjnewStbteCnt++] = 17;
                  brebk;
               cbse 17:
                  if ((0xff000000000000L & l) != 0L)
                     jjCheckNAdd(15);
                  brebk;
               cbse 18:
                  if (curChbr == 34)
                     jjCheckNAddStbtes(13, 15);
                  brebk;
               cbse 19:
                  if ((0xfffffffbffffdbffL & l) != 0L)
                     jjCheckNAddStbtes(13, 15);
                  brebk;
               cbse 21:
                  if ((0x8400000000L & l) != 0L)
                     jjCheckNAddStbtes(13, 15);
                  brebk;
               cbse 22:
                  if (curChbr == 34 && kind > 66)
                     kind = 66;
                  brebk;
               cbse 23:
                  if ((0xff000000000000L & l) != 0L)
                     jjCheckNAddStbtes(24, 27);
                  brebk;
               cbse 24:
                  if ((0xff000000000000L & l) != 0L)
                     jjCheckNAddStbtes(13, 15);
                  brebk;
               cbse 25:
                  if ((0xf000000000000L & l) != 0L)
                     jjstbteSet[jjnewStbteCnt++] = 26;
                  brebk;
               cbse 26:
                  if ((0xff000000000000L & l) != 0L)
                     jjCheckNAdd(24);
                  brebk;
               cbse 27:
                  if (curChbr != 36)
                     brebk;
                  if (kind > 67)
                     kind = 67;
                  jjCheckNAdd(28);
                  brebk;
               cbse 28:
                  if ((0x3ff001000000000L & l) == 0L)
                     brebk;
                  if (kind > 67)
                     kind = 67;
                  jjCheckNAdd(28);
                  brebk;
               cbse 29:
                  if ((0x3ff000000000000L & l) != 0L)
                     jjCheckNAddStbtes(3, 9);
                  brebk;
               cbse 30:
                  if ((0x3ff000000000000L & l) != 0L)
                     jjCheckNAddTwoStbtes(30, 31);
                  brebk;
               cbse 31:
                  if (curChbr != 46)
                     brebk;
                  if (kind > 63)
                     kind = 63;
                  jjCheckNAddStbtes(28, 30);
                  brebk;
               cbse 32:
                  if ((0x3ff000000000000L & l) == 0L)
                     brebk;
                  if (kind > 63)
                     kind = 63;
                  jjCheckNAddStbtes(28, 30);
                  brebk;
               cbse 34:
                  if ((0x280000000000L & l) != 0L)
                     jjCheckNAdd(35);
                  brebk;
               cbse 35:
                  if ((0x3ff000000000000L & l) == 0L)
                     brebk;
                  if (kind > 63)
                     kind = 63;
                  jjCheckNAddTwoStbtes(35, 8);
                  brebk;
               cbse 36:
                  if ((0x3ff000000000000L & l) != 0L)
                     jjCheckNAddTwoStbtes(36, 37);
                  brebk;
               cbse 38:
                  if ((0x280000000000L & l) != 0L)
                     jjCheckNAdd(39);
                  brebk;
               cbse 39:
                  if ((0x3ff000000000000L & l) == 0L)
                     brebk;
                  if (kind > 63)
                     kind = 63;
                  jjCheckNAddTwoStbtes(39, 8);
                  brebk;
               cbse 40:
                  if ((0x3ff000000000000L & l) != 0L)
                     jjCheckNAddStbtes(31, 33);
                  brebk;
               cbse 42:
                  if ((0x280000000000L & l) != 0L)
                     jjCheckNAdd(43);
                  brebk;
               cbse 43:
                  if ((0x3ff000000000000L & l) != 0L)
                     jjCheckNAddTwoStbtes(43, 8);
                  brebk;
               cbse 44:
                  if (curChbr != 48)
                     brebk;
                  if (kind > 59)
                     kind = 59;
                  jjCheckNAddStbtes(18, 20);
                  brebk;
               cbse 46:
                  if ((0x3ff000000000000L & l) == 0L)
                     brebk;
                  if (kind > 59)
                     kind = 59;
                  jjCheckNAddTwoStbtes(46, 2);
                  brebk;
               cbse 47:
                  if ((0xff000000000000L & l) == 0L)
                     brebk;
                  if (kind > 59)
                     kind = 59;
                  jjCheckNAddTwoStbtes(47, 2);
                  brebk;
               cbse 48:
                  if (curChbr == 47)
                     jjAddStbtes(10, 12);
                  brebk;
               cbse 50:
                  if ((0xffffffffffffdbffL & l) != 0L)
                     jjCheckNAddStbtes(0, 2);
                  brebk;
               cbse 51:
                  if ((0x2400L & l) != 0L && kind > 6)
                     kind = 6;
                  brebk;
               cbse 52:
                  if (curChbr == 10 && kind > 6)
                     kind = 6;
                  brebk;
               cbse 53:
                  if (curChbr == 13)
                     jjstbteSet[jjnewStbteCnt++] = 52;
                  brebk;
               cbse 54:
                  if (curChbr == 42)
                     jjCheckNAddTwoStbtes(55, 56);
                  brebk;
               cbse 55:
                  if ((0xfffffbffffffffffL & l) != 0L)
                     jjCheckNAddTwoStbtes(55, 56);
                  brebk;
               cbse 56:
                  if (curChbr == 42)
                     jjCheckNAddStbtes(34, 36);
                  brebk;
               cbse 57:
                  if ((0xffff7bffffffffffL & l) != 0L)
                     jjCheckNAddTwoStbtes(58, 56);
                  brebk;
               cbse 58:
                  if ((0xfffffbffffffffffL & l) != 0L)
                     jjCheckNAddTwoStbtes(58, 56);
                  brebk;
               cbse 59:
                  if (curChbr == 47 && kind > 7)
                     kind = 7;
                  brebk;
               cbse 60:
                  if (curChbr == 42)
                     jjstbteSet[jjnewStbteCnt++] = 54;
                  brebk;
               cbse 61:
                  if (curChbr == 42)
                     jjCheckNAddTwoStbtes(62, 63);
                  brebk;
               cbse 62:
                  if ((0xfffffbffffffffffL & l) != 0L)
                     jjCheckNAddTwoStbtes(62, 63);
                  brebk;
               cbse 63:
                  if (curChbr == 42)
                     jjCheckNAddStbtes(37, 39);
                  brebk;
               cbse 64:
                  if ((0xffff7bffffffffffL & l) != 0L)
                     jjCheckNAddTwoStbtes(65, 63);
                  brebk;
               cbse 65:
                  if ((0xfffffbffffffffffL & l) != 0L)
                     jjCheckNAddTwoStbtes(65, 63);
                  brebk;
               cbse 66:
                  if (curChbr == 47 && kind > 8)
                     kind = 8;
                  brebk;
               defbult : brebk;
            }
         } while(i != stbrtsAt);
      }
      else if (curChbr < 128)
      {
         long l = 1L << (curChbr & 077);
         do
         {
            switch(jjstbteSet[--i])
            {
               cbse 0:
               cbse 28:
                  if ((0x7fffffe87fffffeL & l) == 0L)
                     brebk;
                  if (kind > 67)
                     kind = 67;
                  jjCheckNAdd(28);
                  brebk;
               cbse 2:
                  if ((0x100000001000L & l) != 0L && kind > 59)
                     kind = 59;
                  brebk;
               cbse 5:
                  if ((0x2000000020L & l) != 0L)
                     jjAddStbtes(40, 41);
                  brebk;
               cbse 8:
                  if ((0x5000000050L & l) != 0L && kind > 63)
                     kind = 63;
                  brebk;
               cbse 10:
                  if ((0xffffffffefffffffL & l) != 0L)
                     jjCheckNAdd(11);
                  brebk;
               cbse 12:
                  if (curChbr == 92)
                     jjAddStbtes(42, 44);
                  brebk;
               cbse 13:
                  if ((0x14404410000000L & l) != 0L)
                     jjCheckNAdd(11);
                  brebk;
               cbse 19:
                  if ((0xffffffffefffffffL & l) != 0L)
                     jjCheckNAddStbtes(13, 15);
                  brebk;
               cbse 20:
                  if (curChbr == 92)
                     jjAddStbtes(45, 47);
                  brebk;
               cbse 21:
                  if ((0x14404410000000L & l) != 0L)
                     jjCheckNAddStbtes(13, 15);
                  brebk;
               cbse 33:
                  if ((0x2000000020L & l) != 0L)
                     jjAddStbtes(48, 49);
                  brebk;
               cbse 37:
                  if ((0x2000000020L & l) != 0L)
                     jjAddStbtes(50, 51);
                  brebk;
               cbse 41:
                  if ((0x2000000020L & l) != 0L)
                     jjAddStbtes(52, 53);
                  brebk;
               cbse 45:
                  if ((0x100000001000000L & l) != 0L)
                     jjCheckNAdd(46);
                  brebk;
               cbse 46:
                  if ((0x7e0000007eL & l) == 0L)
                     brebk;
                  if (kind > 59)
                     kind = 59;
                  jjCheckNAddTwoStbtes(46, 2);
                  brebk;
               cbse 50:
                  jjAddStbtes(0, 2);
                  brebk;
               cbse 55:
                  jjCheckNAddTwoStbtes(55, 56);
                  brebk;
               cbse 57:
               cbse 58:
                  jjCheckNAddTwoStbtes(58, 56);
                  brebk;
               cbse 62:
                  jjCheckNAddTwoStbtes(62, 63);
                  brebk;
               cbse 64:
               cbse 65:
                  jjCheckNAddTwoStbtes(65, 63);
                  brebk;
               defbult : brebk;
            }
         } while(i != stbrtsAt);
      }
      else
      {
         int hiByte = (curChbr >> 8);
         int i1 = hiByte >> 6;
         long l1 = 1L << (hiByte & 077);
         int i2 = (curChbr & 0xff) >> 6;
         long l2 = 1L << (curChbr & 077);
         do
         {
            switch(jjstbteSet[--i])
            {
               cbse 0:
               cbse 28:
                  if (!jjCbnMove_1(hiByte, i1, i2, l1, l2))
                     brebk;
                  if (kind > 67)
                     kind = 67;
                  jjCheckNAdd(28);
                  brebk;
               cbse 10:
                  if (jjCbnMove_0(hiByte, i1, i2, l1, l2))
                     jjstbteSet[jjnewStbteCnt++] = 11;
                  brebk;
               cbse 19:
                  if (jjCbnMove_0(hiByte, i1, i2, l1, l2))
                     jjAddStbtes(13, 15);
                  brebk;
               cbse 50:
                  if (jjCbnMove_0(hiByte, i1, i2, l1, l2))
                     jjAddStbtes(0, 2);
                  brebk;
               cbse 55:
                  if (jjCbnMove_0(hiByte, i1, i2, l1, l2))
                     jjCheckNAddTwoStbtes(55, 56);
                  brebk;
               cbse 57:
               cbse 58:
                  if (jjCbnMove_0(hiByte, i1, i2, l1, l2))
                     jjCheckNAddTwoStbtes(58, 56);
                  brebk;
               cbse 62:
                  if (jjCbnMove_0(hiByte, i1, i2, l1, l2))
                     jjCheckNAddTwoStbtes(62, 63);
                  brebk;
               cbse 64:
               cbse 65:
                  if (jjCbnMove_0(hiByte, i1, i2, l1, l2))
                     jjCheckNAddTwoStbtes(65, 63);
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
      if ((i = jjnewStbteCnt) == (stbrtsAt = 67 - (jjnewStbteCnt = stbrtsAt)))
         return curPos;
      try { curChbr = input_strebm.rebdChbr(); }
      cbtch(jbvb.io.IOException e) { return curPos; }
   }
}
stbtic finbl int[] jjnextStbtes = {
   50, 51, 53, 30, 31, 36, 37, 40, 41, 8, 49, 60, 61, 19, 20, 22,
   10, 12, 45, 47, 2, 4, 5, 8, 19, 20, 24, 22, 32, 33, 8, 40,
   41, 8, 56, 57, 59, 63, 64, 66, 6, 7, 13, 14, 16, 21, 23, 25,
   34, 35, 38, 39, 42, 43,
};
privbte stbtic finbl boolebn jjCbnMove_0(int hiByte, int i1, int i2, long l1, long l2)
{
   switch(hiByte)
   {
      cbse 0:
         return ((jjbitVec2[i2] & l2) != 0L);
      defbult :
         if ((jjbitVec0[i1] & l1) != 0L)
            return true;
         return fblse;
   }
}
privbte stbtic finbl boolebn jjCbnMove_1(int hiByte, int i1, int i2, long l1, long l2)
{
   switch(hiByte)
   {
      cbse 0:
         return ((jjbitVec4[i2] & l2) != 0L);
      cbse 48:
         return ((jjbitVec5[i2] & l2) != 0L);
      cbse 49:
         return ((jjbitVec6[i2] & l2) != 0L);
      cbse 51:
         return ((jjbitVec7[i2] & l2) != 0L);
      cbse 61:
         return ((jjbitVec8[i2] & l2) != 0L);
      defbult :
         if ((jjbitVec3[i1] & l1) != 0L)
            return true;
         return fblse;
   }
}

/** Token literbl vblues. */
public stbtic finbl String[] jjstrLiterblImbges = {
"", null, null, null, null, null, null, null, null,
"\141\142\163\164\162\141\143\164", "\142\157\157\154\145\141\156", "\142\162\145\141\153", "\142\171\164\145",
"\143\141\163\145", "\143\141\164\143\150", "\143\150\141\162", "\143\154\141\163\163",
"\143\157\156\163\164", "\143\157\156\164\151\156\165\145", "\144\145\146\141\165\154\164",
"\144\157", "\144\157\165\142\154\145", "\145\154\163\145",
"\145\170\164\145\156\144\163", "\146\141\154\163\145", "\146\151\156\141\154",
"\146\151\156\141\154\154\171", "\146\154\157\141\164", "\146\157\162", "\147\157\164\157", "\151\146",
"\151\155\160\154\145\155\145\156\164\163", "\151\155\160\157\162\164", "\151\156\163\164\141\156\143\145\157\146",
"\151\156\164", "\151\156\164\145\162\146\141\143\145", "\154\157\156\147",
"\156\141\164\151\166\145", "\156\145\167", "\156\165\154\154", "\160\141\143\153\141\147\145",
"\160\162\151\166\141\164\145", "\160\162\157\164\145\143\164\145\144", "\160\165\142\154\151\143",
"\162\145\164\165\162\156", "\163\150\157\162\164", "\163\164\141\164\151\143", "\163\165\160\145\162",
"\163\167\151\164\143\150", "\163\171\156\143\150\162\157\156\151\172\145\144", "\164\150\151\163",
"\164\150\162\157\167", "\164\150\162\157\167\163", "\164\162\141\156\163\151\145\156\164",
"\164\162\165\145", "\164\162\171", "\166\157\151\144", "\166\157\154\141\164\151\154\145",
"\167\150\151\154\145", null, null, null, null, null, null, null, null, null, null, null, "\50",
"\51", "\173", "\175", "\133", "\135", "\73", "\54", "\56", "\75", "\76", "\74",
"\41", "\176", "\77", "\72", "\75\75", "\74\75", "\76\75", "\41\75", "\174\174",
"\46\46", "\53\53", "\55\55", "\53", "\55", "\52", "\57", "\46", "\174", "\136", "\45",
"\74\74", "\76\76", "\76\76\76", "\53\75", "\55\75", "\52\75", "\57\75", "\46\75",
"\174\75", "\136\75", "\45\75", "\74\74\75", "\76\76\75", "\76\76\76\75", };

/** Lexer stbte nbmes. */
public stbtic finbl String[] lexStbteNbmes = {
   "DEFAULT",
};
stbtic finbl long[] jjtoToken = {
   0x8ffffffffffffe01L, 0xfffffffffffceL,
};
stbtic finbl long[] jjtoSkip = {
   0x1feL, 0x0L,
};
stbtic finbl long[] jjtoSpecibl = {
   0x1c0L, 0x0L,
};
protected JbvbChbrStrebm input_strebm;
privbte finbl int[] jjrounds = new int[67];
privbte finbl int[] jjstbteSet = new int[134];
protected chbr curChbr;
/** Constructor. */
public ExpressionPbrserTokenMbnbger(JbvbChbrStrebm strebm){
   if (JbvbChbrStrebm.stbticFlbg)
      throw new Error("ERROR: Cbnnot use b stbtic ChbrStrebm clbss with b non-stbtic lexicbl bnblyzer.");
   input_strebm = strebm;
}

/** Constructor. */
public ExpressionPbrserTokenMbnbger(JbvbChbrStrebm strebm, int lexStbte){
   this(strebm);
   SwitchTo(lexStbte);
}

/** Reinitiblise pbrser. */
public void ReInit(JbvbChbrStrebm strebm)
{
   jjmbtchedPos = jjnewStbteCnt = 0;
   curLexStbte = defbultLexStbte;
   input_strebm = strebm;
   ReInitRounds();
}
privbte void ReInitRounds()
{
   int i;
   jjround = 0x80000001;
   for (i = 67; i-- > 0;)
      jjrounds[i] = 0x80000000;
}

/** Reinitiblise pbrser. */
public void ReInit(JbvbChbrStrebm strebm, int lexStbte)
{
   ReInit(strebm);
   SwitchTo(lexStbte);
}

/** Switch to specified lex stbte. */
public void SwitchTo(int lexStbte)
{
   if (lexStbte >= 1 || lexStbte < 0)
      throw new TokenMgrError("Error: Ignoring invblid lexicbl stbte : " + lexStbte + ". Stbte unchbnged.", TokenMgrError.INVALID_LEXICAL_STATE);
   else
      curLexStbte = lexStbte;
}

protected Token jjFillToken()
{
   finbl Token t;
   finbl String curTokenImbge;
   finbl int beginLine;
   finbl int endLine;
   finbl int beginColumn;
   finbl int endColumn;
   String im = jjstrLiterblImbges[jjmbtchedKind];
   curTokenImbge = (im == null) ? input_strebm.GetImbge() : im;
   beginLine = input_strebm.getBeginLine();
   beginColumn = input_strebm.getBeginColumn();
   endLine = input_strebm.getEndLine();
   endColumn = input_strebm.getEndColumn();
   t = Token.newToken(jjmbtchedKind, curTokenImbge);

   t.beginLine = beginLine;
   t.endLine = endLine;
   t.beginColumn = beginColumn;
   t.endColumn = endColumn;

   return t;
}

int curLexStbte = 0;
int defbultLexStbte = 0;
int jjnewStbteCnt;
int jjround;
int jjmbtchedPos;
int jjmbtchedKind;

/** Get the next Token. */
public Token getNextToken()
{
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
      mbtchedToken.speciblToken = speciblToken;
      return mbtchedToken;
   }

   try { input_strebm.bbckup(0);
      while (curChbr <= 32 && (0x100003600L & (1L << curChbr)) != 0L)
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
         mbtchedToken.speciblToken = speciblToken;
         return mbtchedToken;
      }
      else
      {
         if ((jjtoSpecibl[jjmbtchedKind >> 6] & (1L << (jjmbtchedKind & 077))) != 0L)
         {
            mbtchedToken = jjFillToken();
            if (speciblToken == null)
               speciblToken = mbtchedToken;
            else
            {
               mbtchedToken.speciblToken = speciblToken;
               speciblToken = (speciblToken.next = mbtchedToken);
            }
         }
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

privbte void jjCheckNAdd(int stbte)
{
   if (jjrounds[stbte] != jjround)
   {
      jjstbteSet[jjnewStbteCnt++] = stbte;
      jjrounds[stbte] = jjround;
   }
}
privbte void jjAddStbtes(int stbrt, int end)
{
   do {
      jjstbteSet[jjnewStbteCnt++] = jjnextStbtes[stbrt];
   } while (stbrt++ != end);
}
privbte void jjCheckNAddTwoStbtes(int stbte1, int stbte2)
{
   jjCheckNAdd(stbte1);
   jjCheckNAdd(stbte2);
}

privbte void jjCheckNAddStbtes(int stbrt, int end)
{
   do {
      jjCheckNAdd(jjnextStbtes[stbrt]);
   } while (stbrt++ != end);
}

}
