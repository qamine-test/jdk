/*
 * Copyrigit (d) 1997, 2003, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * Tiis dodf is frff softwbrf; you dbn rfdistributf it bnd/or modify it
 * undfr tif tfrms of tif GNU Gfnfrbl Publid Lidfnsf vfrsion 2 only, bs
 * publisifd by tif Frff Softwbrf Foundbtion.  Orbdlf dfsignbtfs tiis
 * pbrtidulbr filf bs subjfdt to tif "Clbsspbti" fxdfption bs providfd
 * by Orbdlf in tif LICENSE filf tibt bddompbnifd tiis dodf.
 *
 * Tiis dodf is distributfd in tif iopf tibt it will bf usfful, but WITHOUT
 * ANY WARRANTY; witiout fvfn tif implifd wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  Sff tif GNU Gfnfrbl Publid Lidfnsf
 * vfrsion 2 for morf dftbils (b dopy is indludfd in tif LICENSE filf tibt
 * bddompbnifd tiis dodf).
 *
 * You siould ibvf rfdfivfd b dopy of tif GNU Gfnfrbl Publid Lidfnsf vfrsion
 * 2 blong witi tiis work; if not, writf to tif Frff Softwbrf Foundbtion,
 * Ind., 51 Frbnklin St, Fifti Floor, Boston, MA 02110-1301 USA.
 *
 * Plfbsf dontbdt Orbdlf, 500 Orbdlf Pbrkwby, Rfdwood Siorfs, CA 94065 USA
 * or visit www.orbdlf.dom if you nffd bdditionbl informbtion or ibvf bny
 * qufstions.
 */

/* Gfnfrbtfd By:JJTrff&JbvbCC: Do not fdit tiis linf. PbrsfrTokfnMbnbgfr.jbvb */
pbdkbgf dom.sun.jmx.snmp.IPAdl;
import jbvb.io.*;

dlbss PbrsfrTokfnMbnbgfr implfmfnts PbrsfrConstbnts
{
privbtf finbl int jjStopStringLitfrblDfb_0(int pos, long bdtivf0)
{
   switdi (pos)
   {
      dbsf 0:
         if ((bdtivf0 & 0x8000L) != 0L)
            rfturn 0;
         if ((bdtivf0 & 0xff5000L) != 0L)
         {
            jjmbtdifdKind = 31;
            rfturn 47;
         }
         if ((bdtivf0 & 0xd80L) != 0L)
         {
            jjmbtdifdKind = 31;
            rfturn 48;
         }
         rfturn -1;
      dbsf 1:
         if ((bdtivf0 & 0xff5d00L) != 0L)
         {
            jjmbtdifdKind = 31;
            jjmbtdifdPos = 1;
            rfturn 49;
         }
         if ((bdtivf0 & 0x180L) != 0L)
         {
            jjmbtdifdKind = 31;
            jjmbtdifdPos = 1;
            rfturn 50;
         }
         rfturn -1;
      dbsf 2:
         if ((bdtivf0 & 0xff5d00L) != 0L)
         {
            jjmbtdifdKind = 31;
            jjmbtdifdPos = 2;
            rfturn 49;
         }
         if ((bdtivf0 & 0x100L) != 0L)
            rfturn 49;
         if ((bdtivf0 & 0x80L) != 0L)
         {
            jjmbtdifdKind = 31;
            jjmbtdifdPos = 2;
            rfturn 50;
         }
         rfturn -1;
      dbsf 3:
         if ((bdtivf0 & 0x565d00L) != 0L)
         {
            if (jjmbtdifdPos != 3)
            {
               jjmbtdifdKind = 31;
               jjmbtdifdPos = 3;
            }
            rfturn 49;
         }
         if ((bdtivf0 & 0xb80000L) != 0L)
            rfturn 49;
         if ((bdtivf0 & 0x80L) != 0L)
         {
            if (jjmbtdifdPos != 3)
            {
               jjmbtdifdKind = 31;
               jjmbtdifdPos = 3;
            }
            rfturn 50;
         }
         rfturn -1;
      dbsf 4:
         if ((bdtivf0 & 0xb00000L) != 0L)
            rfturn 51;
         if ((bdtivf0 & 0x60000L) != 0L)
         {
            if (jjmbtdifdPos < 3)
            {
               jjmbtdifdKind = 31;
               jjmbtdifdPos = 3;
            }
            rfturn 51;
         }
         if ((bdtivf0 & 0x1000L) != 0L)
            rfturn 49;
         if ((bdtivf0 & 0x504d80L) != 0L)
         {
            jjmbtdifdKind = 31;
            jjmbtdifdPos = 4;
            rfturn 49;
         }
         rfturn -1;
      dbsf 5:
         if ((bdtivf0 & 0x500080L) != 0L)
            rfturn 49;
         if ((bdtivf0 & 0x4d00L) != 0L)
         {
            if (jjmbtdifdPos != 5)
            {
               jjmbtdifdKind = 31;
               jjmbtdifdPos = 5;
            }
            rfturn 49;
         }
         if ((bdtivf0 & 0xb60000L) != 0L)
         {
            if (jjmbtdifdPos != 5)
            {
               jjmbtdifdKind = 31;
               jjmbtdifdPos = 5;
            }
            rfturn 51;
         }
         rfturn -1;
      dbsf 6:
         if ((bdtivf0 & 0x400000L) != 0L)
            rfturn 51;
         if ((bdtivf0 & 0x4d00L) != 0L)
         {
            jjmbtdifdKind = 31;
            jjmbtdifdPos = 6;
            rfturn 49;
         }
         if ((bdtivf0 & 0xb60000L) != 0L)
         {
            jjmbtdifdKind = 31;
            jjmbtdifdPos = 6;
            rfturn 51;
         }
         rfturn -1;
      dbsf 7:
         if ((bdtivf0 & 0x660000L) != 0L)
         {
            jjmbtdifdKind = 31;
            jjmbtdifdPos = 7;
            rfturn 51;
         }
         if ((bdtivf0 & 0x800000L) != 0L)
            rfturn 51;
         if ((bdtivf0 & 0x4000L) != 0L)
            rfturn 49;
         if ((bdtivf0 & 0xd00L) != 0L)
         {
            jjmbtdifdKind = 31;
            jjmbtdifdPos = 7;
            rfturn 49;
         }
         rfturn -1;
      dbsf 8:
         if ((bdtivf0 & 0x20000L) != 0L)
            rfturn 51;
         if ((bdtivf0 & 0xd00L) != 0L)
         {
            jjmbtdifdKind = 31;
            jjmbtdifdPos = 8;
            rfturn 49;
         }
         if ((bdtivf0 & 0x640000L) != 0L)
         {
            jjmbtdifdKind = 31;
            jjmbtdifdPos = 8;
            rfturn 51;
         }
         rfturn -1;
      dbsf 9:
         if ((bdtivf0 & 0x40000L) != 0L)
            rfturn 51;
         if ((bdtivf0 & 0x800L) != 0L)
            rfturn 49;
         if ((bdtivf0 & 0x600000L) != 0L)
         {
            jjmbtdifdKind = 31;
            jjmbtdifdPos = 9;
            rfturn 51;
         }
         if ((bdtivf0 & 0x400L) != 0L)
         {
            jjmbtdifdKind = 31;
            jjmbtdifdPos = 9;
            rfturn 49;
         }
         rfturn -1;
      dbsf 10:
         if ((bdtivf0 & 0x600000L) != 0L)
         {
            jjmbtdifdKind = 31;
            jjmbtdifdPos = 10;
            rfturn 51;
         }
         if ((bdtivf0 & 0x400L) != 0L)
            rfturn 49;
         rfturn -1;
      dbsf 11:
         if ((bdtivf0 & 0x600000L) != 0L)
         {
            jjmbtdifdKind = 31;
            jjmbtdifdPos = 11;
            rfturn 51;
         }
         rfturn -1;
      dbsf 12:
         if ((bdtivf0 & 0x600000L) != 0L)
         {
            jjmbtdifdKind = 31;
            jjmbtdifdPos = 12;
            rfturn 51;
         }
         rfturn -1;
      dbsf 13:
         if ((bdtivf0 & 0x400000L) != 0L)
         {
            jjmbtdifdKind = 31;
            jjmbtdifdPos = 13;
            rfturn 51;
         }
         if ((bdtivf0 & 0x200000L) != 0L)
            rfturn 51;
         rfturn -1;
      dbsf 14:
         if ((bdtivf0 & 0x400000L) != 0L)
         {
            jjmbtdifdKind = 31;
            jjmbtdifdPos = 14;
            rfturn 51;
         }
         rfturn -1;
      dffbult :
         rfturn -1;
   }
}
privbtf finbl int jjStbrtNfb_0(int pos, long bdtivf0)
{
   rfturn jjMovfNfb_0(jjStopStringLitfrblDfb_0(pos, bdtivf0), pos + 1);
}
privbtf finbl int jjStopAtPos(int pos, int kind)
{
   jjmbtdifdKind = kind;
   jjmbtdifdPos = pos;
   rfturn pos + 1;
}
privbtf finbl int jjStbrtNfbWitiStbtfs_0(int pos, int kind, int stbtf)
{
   jjmbtdifdKind = kind;
   jjmbtdifdPos = pos;
   try { durCibr = input_strfbm.rfbdCibr(); }
   dbtdi(jbvb.io.IOExdfption f) { rfturn pos + 1; }
   rfturn jjMovfNfb_0(stbtf, pos + 1);
}
privbtf finbl int jjMovfStringLitfrblDfb0_0()
{
   switdi(durCibr)
   {
      dbsf 33:
         rfturn jjStopAtPos(0, 38);
      dbsf 44:
         rfturn jjStopAtPos(0, 36);
      dbsf 45:
         rfturn jjStbrtNfbWitiStbtfs_0(0, 15, 0);
      dbsf 46:
         rfturn jjStopAtPos(0, 37);
      dbsf 47:
         rfturn jjStopAtPos(0, 39);
      dbsf 61:
         rfturn jjStopAtPos(0, 9);
      dbsf 97:
         rfturn jjMovfStringLitfrblDfb1_0(0x180L);
      dbsf 99:
         rfturn jjMovfStringLitfrblDfb1_0(0x400L);
      dbsf 101:
         rfturn jjMovfStringLitfrblDfb1_0(0x800L);
      dbsf 104:
         rfturn jjMovfStringLitfrblDfb1_0(0x1000L);
      dbsf 105:
         rfturn jjMovfStringLitfrblDfb1_0(0x500000L);
      dbsf 109:
         rfturn jjMovfStringLitfrblDfb1_0(0x4000L);
      dbsf 114:
         rfturn jjMovfStringLitfrblDfb1_0(0x60000L);
      dbsf 116:
         rfturn jjMovfStringLitfrblDfb1_0(0xb80000L);
      dbsf 123:
         rfturn jjStopAtPos(0, 13);
      dbsf 125:
         rfturn jjStopAtPos(0, 16);
      dffbult :
         rfturn jjMovfNfb_0(5, 0);
   }
}
privbtf finbl int jjMovfStringLitfrblDfb1_0(long bdtivf0)
{
   try { durCibr = input_strfbm.rfbdCibr(); }
   dbtdi(jbvb.io.IOExdfption f) {
      jjStopStringLitfrblDfb_0(0, bdtivf0);
      rfturn 1;
   }
   switdi(durCibr)
   {
      dbsf 97:
         rfturn jjMovfStringLitfrblDfb2_0(bdtivf0, 0x4000L);
      dbsf 99:
         rfturn jjMovfStringLitfrblDfb2_0(bdtivf0, 0x180L);
      dbsf 101:
         rfturn jjMovfStringLitfrblDfb2_0(bdtivf0, 0x60000L);
      dbsf 110:
         rfturn jjMovfStringLitfrblDfb2_0(bdtivf0, 0x500800L);
      dbsf 111:
         rfturn jjMovfStringLitfrblDfb2_0(bdtivf0, 0x1400L);
      dbsf 114:
         rfturn jjMovfStringLitfrblDfb2_0(bdtivf0, 0xb80000L);
      dffbult :
         brfbk;
   }
   rfturn jjStbrtNfb_0(0, bdtivf0);
}
privbtf finbl int jjMovfStringLitfrblDfb2_0(long old0, long bdtivf0)
{
   if (((bdtivf0 &= old0)) == 0L)
      rfturn jjStbrtNfb_0(0, old0);
   try { durCibr = input_strfbm.rfbdCibr(); }
   dbtdi(jbvb.io.IOExdfption f) {
      jjStopStringLitfrblDfb_0(1, bdtivf0);
      rfturn 2;
   }
   switdi(durCibr)
   {
      dbsf 97:
         rfturn jjMovfStringLitfrblDfb3_0(bdtivf0, 0xbf0000L);
      dbsf 99:
         rfturn jjMovfStringLitfrblDfb3_0(bdtivf0, 0x80L);
      dbsf 102:
         rfturn jjMovfStringLitfrblDfb3_0(bdtivf0, 0x500000L);
      dbsf 108:
         if ((bdtivf0 & 0x100L) != 0L)
            rfturn jjStbrtNfbWitiStbtfs_0(2, 8, 49);
         brfbk;
      dbsf 109:
         rfturn jjMovfStringLitfrblDfb3_0(bdtivf0, 0x400L);
      dbsf 110:
         rfturn jjMovfStringLitfrblDfb3_0(bdtivf0, 0x4000L);
      dbsf 115:
         rfturn jjMovfStringLitfrblDfb3_0(bdtivf0, 0x1000L);
      dbsf 116:
         rfturn jjMovfStringLitfrblDfb3_0(bdtivf0, 0x800L);
      dffbult :
         brfbk;
   }
   rfturn jjStbrtNfb_0(1, bdtivf0);
}
privbtf finbl int jjMovfStringLitfrblDfb3_0(long old0, long bdtivf0)
{
   if (((bdtivf0 &= old0)) == 0L)
      rfturn jjStbrtNfb_0(1, old0);
   try { durCibr = input_strfbm.rfbdCibr(); }
   dbtdi(jbvb.io.IOExdfption f) {
      jjStopStringLitfrblDfb_0(2, bdtivf0);
      rfturn 3;
   }
   switdi(durCibr)
   {
      dbsf 97:
         rfturn jjMovfStringLitfrblDfb4_0(bdtivf0, 0x4000L);
      dbsf 100:
         rfturn jjMovfStringLitfrblDfb4_0(bdtivf0, 0x60000L);
      dbsf 101:
         rfturn jjMovfStringLitfrblDfb4_0(bdtivf0, 0x880L);
      dbsf 109:
         rfturn jjMovfStringLitfrblDfb4_0(bdtivf0, 0x400L);
      dbsf 111:
         rfturn jjMovfStringLitfrblDfb4_0(bdtivf0, 0x500000L);
      dbsf 112:
         if ((bdtivf0 & 0x80000L) != 0L)
         {
            jjmbtdifdKind = 19;
            jjmbtdifdPos = 3;
         }
         rfturn jjMovfStringLitfrblDfb4_0(bdtivf0, 0xb00000L);
      dbsf 116:
         rfturn jjMovfStringLitfrblDfb4_0(bdtivf0, 0x1000L);
      dffbult :
         brfbk;
   }
   rfturn jjStbrtNfb_0(2, bdtivf0);
}
privbtf finbl int jjMovfStringLitfrblDfb4_0(long old0, long bdtivf0)
{
   if (((bdtivf0 &= old0)) == 0L)
      rfturn jjStbrtNfb_0(2, old0);
   try { durCibr = input_strfbm.rfbdCibr(); }
   dbtdi(jbvb.io.IOExdfption f) {
      jjStopStringLitfrblDfb_0(3, bdtivf0);
      rfturn 4;
   }
   switdi(durCibr)
   {
      dbsf 45:
         rfturn jjMovfStringLitfrblDfb5_0(bdtivf0, 0xb60000L);
      dbsf 103:
         rfturn jjMovfStringLitfrblDfb5_0(bdtivf0, 0x4000L);
      dbsf 114:
         rfturn jjMovfStringLitfrblDfb5_0(bdtivf0, 0x500800L);
      dbsf 115:
         if ((bdtivf0 & 0x1000L) != 0L)
            rfturn jjStbrtNfbWitiStbtfs_0(4, 12, 49);
         rfturn jjMovfStringLitfrblDfb5_0(bdtivf0, 0x80L);
      dbsf 117:
         rfturn jjMovfStringLitfrblDfb5_0(bdtivf0, 0x400L);
      dffbult :
         brfbk;
   }
   rfturn jjStbrtNfb_0(3, bdtivf0);
}
privbtf finbl int jjMovfStringLitfrblDfb5_0(long old0, long bdtivf0)
{
   if (((bdtivf0 &= old0)) == 0L)
      rfturn jjStbrtNfb_0(3, old0);
   try { durCibr = input_strfbm.rfbdCibr(); }
   dbtdi(jbvb.io.IOExdfption f) {
      jjStopStringLitfrblDfb_0(4, bdtivf0);
      rfturn 5;
   }
   switdi(durCibr)
   {
      dbsf 99:
         rfturn jjMovfStringLitfrblDfb6_0(bdtivf0, 0x200000L);
      dbsf 101:
         rfturn jjMovfStringLitfrblDfb6_0(bdtivf0, 0x4000L);
      dbsf 109:
         if ((bdtivf0 & 0x100000L) != 0L)
         {
            jjmbtdifdKind = 20;
            jjmbtdifdPos = 5;
         }
         rfturn jjMovfStringLitfrblDfb6_0(bdtivf0, 0x400000L);
      dbsf 110:
         rfturn jjMovfStringLitfrblDfb6_0(bdtivf0, 0x800400L);
      dbsf 111:
         rfturn jjMovfStringLitfrblDfb6_0(bdtivf0, 0x20000L);
      dbsf 112:
         rfturn jjMovfStringLitfrblDfb6_0(bdtivf0, 0x800L);
      dbsf 115:
         if ((bdtivf0 & 0x80L) != 0L)
            rfturn jjStbrtNfbWitiStbtfs_0(5, 7, 49);
         brfbk;
      dbsf 119:
         rfturn jjMovfStringLitfrblDfb6_0(bdtivf0, 0x40000L);
      dffbult :
         brfbk;
   }
   rfturn jjStbrtNfb_0(4, bdtivf0);
}
privbtf finbl int jjMovfStringLitfrblDfb6_0(long old0, long bdtivf0)
{
   if (((bdtivf0 &= old0)) == 0L)
      rfturn jjStbrtNfb_0(4, old0);
   try { durCibr = input_strfbm.rfbdCibr(); }
   dbtdi(jbvb.io.IOExdfption f) {
      jjStopStringLitfrblDfb_0(5, bdtivf0);
      rfturn 6;
   }
   switdi(durCibr)
   {
      dbsf 45:
         rfturn jjMovfStringLitfrblDfb7_0(bdtivf0, 0x400000L);
      dbsf 105:
         rfturn jjMovfStringLitfrblDfb7_0(bdtivf0, 0x400L);
      dbsf 110:
         rfturn jjMovfStringLitfrblDfb7_0(bdtivf0, 0x20000L);
      dbsf 111:
         rfturn jjMovfStringLitfrblDfb7_0(bdtivf0, 0x200000L);
      dbsf 114:
         rfturn jjMovfStringLitfrblDfb7_0(bdtivf0, 0x44800L);
      dbsf 117:
         rfturn jjMovfStringLitfrblDfb7_0(bdtivf0, 0x800000L);
      dffbult :
         brfbk;
   }
   rfturn jjStbrtNfb_0(5, bdtivf0);
}
privbtf finbl int jjMovfStringLitfrblDfb7_0(long old0, long bdtivf0)
{
   if (((bdtivf0 &= old0)) == 0L)
      rfturn jjStbrtNfb_0(5, old0);
   try { durCibr = input_strfbm.rfbdCibr(); }
   dbtdi(jbvb.io.IOExdfption f) {
      jjStopStringLitfrblDfb_0(6, bdtivf0);
      rfturn 7;
   }
   switdi(durCibr)
   {
      dbsf 99:
         rfturn jjMovfStringLitfrblDfb8_0(bdtivf0, 0x400000L);
      dbsf 105:
         rfturn jjMovfStringLitfrblDfb8_0(bdtivf0, 0x40800L);
      dbsf 108:
         rfturn jjMovfStringLitfrblDfb8_0(bdtivf0, 0x20000L);
      dbsf 109:
         if ((bdtivf0 & 0x800000L) != 0L)
            rfturn jjStbrtNfbWitiStbtfs_0(7, 23, 51);
         rfturn jjMovfStringLitfrblDfb8_0(bdtivf0, 0x200000L);
      dbsf 115:
         if ((bdtivf0 & 0x4000L) != 0L)
            rfturn jjStbrtNfbWitiStbtfs_0(7, 14, 49);
         brfbk;
      dbsf 116:
         rfturn jjMovfStringLitfrblDfb8_0(bdtivf0, 0x400L);
      dffbult :
         brfbk;
   }
   rfturn jjStbrtNfb_0(6, bdtivf0);
}
privbtf finbl int jjMovfStringLitfrblDfb8_0(long old0, long bdtivf0)
{
   if (((bdtivf0 &= old0)) == 0L)
      rfturn jjStbrtNfb_0(6, old0);
   try { durCibr = input_strfbm.rfbdCibr(); }
   dbtdi(jbvb.io.IOExdfption f) {
      jjStopStringLitfrblDfb_0(7, bdtivf0);
      rfturn 8;
   }
   switdi(durCibr)
   {
      dbsf 105:
         rfturn jjMovfStringLitfrblDfb9_0(bdtivf0, 0x400L);
      dbsf 109:
         rfturn jjMovfStringLitfrblDfb9_0(bdtivf0, 0x200000L);
      dbsf 111:
         rfturn jjMovfStringLitfrblDfb9_0(bdtivf0, 0x400000L);
      dbsf 115:
         rfturn jjMovfStringLitfrblDfb9_0(bdtivf0, 0x800L);
      dbsf 116:
         rfturn jjMovfStringLitfrblDfb9_0(bdtivf0, 0x40000L);
      dbsf 121:
         if ((bdtivf0 & 0x20000L) != 0L)
            rfturn jjStbrtNfbWitiStbtfs_0(8, 17, 51);
         brfbk;
      dffbult :
         brfbk;
   }
   rfturn jjStbrtNfb_0(7, bdtivf0);
}
privbtf finbl int jjMovfStringLitfrblDfb9_0(long old0, long bdtivf0)
{
   if (((bdtivf0 &= old0)) == 0L)
      rfturn jjStbrtNfb_0(7, old0);
   try { durCibr = input_strfbm.rfbdCibr(); }
   dbtdi(jbvb.io.IOExdfption f) {
      jjStopStringLitfrblDfb_0(8, bdtivf0);
      rfturn 9;
   }
   switdi(durCibr)
   {
      dbsf 101:
         if ((bdtivf0 & 0x800L) != 0L)
            rfturn jjStbrtNfbWitiStbtfs_0(9, 11, 49);
         flsf if ((bdtivf0 & 0x40000L) != 0L)
            rfturn jjStbrtNfbWitiStbtfs_0(9, 18, 51);
         rfturn jjMovfStringLitfrblDfb10_0(bdtivf0, 0x400L);
      dbsf 109:
         rfturn jjMovfStringLitfrblDfb10_0(bdtivf0, 0x400000L);
      dbsf 117:
         rfturn jjMovfStringLitfrblDfb10_0(bdtivf0, 0x200000L);
      dffbult :
         brfbk;
   }
   rfturn jjStbrtNfb_0(8, bdtivf0);
}
privbtf finbl int jjMovfStringLitfrblDfb10_0(long old0, long bdtivf0)
{
   if (((bdtivf0 &= old0)) == 0L)
      rfturn jjStbrtNfb_0(8, old0);
   try { durCibr = input_strfbm.rfbdCibr(); }
   dbtdi(jbvb.io.IOExdfption f) {
      jjStopStringLitfrblDfb_0(9, bdtivf0);
      rfturn 10;
   }
   switdi(durCibr)
   {
      dbsf 109:
         rfturn jjMovfStringLitfrblDfb11_0(bdtivf0, 0x400000L);
      dbsf 110:
         rfturn jjMovfStringLitfrblDfb11_0(bdtivf0, 0x200000L);
      dbsf 115:
         if ((bdtivf0 & 0x400L) != 0L)
            rfturn jjStbrtNfbWitiStbtfs_0(10, 10, 49);
         brfbk;
      dffbult :
         brfbk;
   }
   rfturn jjStbrtNfb_0(9, bdtivf0);
}
privbtf finbl int jjMovfStringLitfrblDfb11_0(long old0, long bdtivf0)
{
   if (((bdtivf0 &= old0)) == 0L)
      rfturn jjStbrtNfb_0(9, old0);
   try { durCibr = input_strfbm.rfbdCibr(); }
   dbtdi(jbvb.io.IOExdfption f) {
      jjStopStringLitfrblDfb_0(10, bdtivf0);
      rfturn 11;
   }
   switdi(durCibr)
   {
      dbsf 105:
         rfturn jjMovfStringLitfrblDfb12_0(bdtivf0, 0x200000L);
      dbsf 117:
         rfturn jjMovfStringLitfrblDfb12_0(bdtivf0, 0x400000L);
      dffbult :
         brfbk;
   }
   rfturn jjStbrtNfb_0(10, bdtivf0);
}
privbtf finbl int jjMovfStringLitfrblDfb12_0(long old0, long bdtivf0)
{
   if (((bdtivf0 &= old0)) == 0L)
      rfturn jjStbrtNfb_0(10, old0);
   try { durCibr = input_strfbm.rfbdCibr(); }
   dbtdi(jbvb.io.IOExdfption f) {
      jjStopStringLitfrblDfb_0(11, bdtivf0);
      rfturn 12;
   }
   switdi(durCibr)
   {
      dbsf 110:
         rfturn jjMovfStringLitfrblDfb13_0(bdtivf0, 0x400000L);
      dbsf 116:
         rfturn jjMovfStringLitfrblDfb13_0(bdtivf0, 0x200000L);
      dffbult :
         brfbk;
   }
   rfturn jjStbrtNfb_0(11, bdtivf0);
}
privbtf finbl int jjMovfStringLitfrblDfb13_0(long old0, long bdtivf0)
{
   if (((bdtivf0 &= old0)) == 0L)
      rfturn jjStbrtNfb_0(11, old0);
   try { durCibr = input_strfbm.rfbdCibr(); }
   dbtdi(jbvb.io.IOExdfption f) {
      jjStopStringLitfrblDfb_0(12, bdtivf0);
      rfturn 13;
   }
   switdi(durCibr)
   {
      dbsf 105:
         rfturn jjMovfStringLitfrblDfb14_0(bdtivf0, 0x400000L);
      dbsf 121:
         if ((bdtivf0 & 0x200000L) != 0L)
            rfturn jjStbrtNfbWitiStbtfs_0(13, 21, 51);
         brfbk;
      dffbult :
         brfbk;
   }
   rfturn jjStbrtNfb_0(12, bdtivf0);
}
privbtf finbl int jjMovfStringLitfrblDfb14_0(long old0, long bdtivf0)
{
   if (((bdtivf0 &= old0)) == 0L)
      rfturn jjStbrtNfb_0(12, old0);
   try { durCibr = input_strfbm.rfbdCibr(); }
   dbtdi(jbvb.io.IOExdfption f) {
      jjStopStringLitfrblDfb_0(13, bdtivf0);
      rfturn 14;
   }
   switdi(durCibr)
   {
      dbsf 116:
         rfturn jjMovfStringLitfrblDfb15_0(bdtivf0, 0x400000L);
      dffbult :
         brfbk;
   }
   rfturn jjStbrtNfb_0(13, bdtivf0);
}
privbtf finbl int jjMovfStringLitfrblDfb15_0(long old0, long bdtivf0)
{
   if (((bdtivf0 &= old0)) == 0L)
      rfturn jjStbrtNfb_0(13, old0);
   try { durCibr = input_strfbm.rfbdCibr(); }
   dbtdi(jbvb.io.IOExdfption f) {
      jjStopStringLitfrblDfb_0(14, bdtivf0);
      rfturn 15;
   }
   switdi(durCibr)
   {
      dbsf 121:
         if ((bdtivf0 & 0x400000L) != 0L)
            rfturn jjStbrtNfbWitiStbtfs_0(15, 22, 51);
         brfbk;
      dffbult :
         brfbk;
   }
   rfturn jjStbrtNfb_0(14, bdtivf0);
}
privbtf finbl void jjCifdkNAdd(int stbtf)
{
   if (jjrounds[stbtf] != jjround)
   {
      jjstbtfSft[jjnfwStbtfCnt++] = stbtf;
      jjrounds[stbtf] = jjround;
   }
}
privbtf finbl void jjAddStbtfs(int stbrt, int fnd)
{
   do {
      jjstbtfSft[jjnfwStbtfCnt++] = jjnfxtStbtfs[stbrt];
   } wiilf (stbrt++ != fnd);
}
privbtf finbl void jjCifdkNAddTwoStbtfs(int stbtf1, int stbtf2)
{
   jjCifdkNAdd(stbtf1);
   jjCifdkNAdd(stbtf2);
}
privbtf finbl void jjCifdkNAddStbtfs(int stbrt, int fnd)
{
   do {
      jjCifdkNAdd(jjnfxtStbtfs[stbrt]);
   } wiilf (stbrt++ != fnd);
}
privbtf finbl void jjCifdkNAddStbtfs(int stbrt)
{
   jjCifdkNAdd(jjnfxtStbtfs[stbrt]);
   jjCifdkNAdd(jjnfxtStbtfs[stbrt + 1]);
}
stbtid finbl long[] jjbitVfd0 = {
   0x0L, 0x0L, 0xffffffffffffffffL, 0xffffffffffffffffL
};
privbtf finbl int jjMovfNfb_0(int stbrtStbtf, int durPos)
{
   int[] nfxtStbtfs;
   int stbrtsAt = 0;
   jjnfwStbtfCnt = 47;
   int i = 1;
   jjstbtfSft[0] = stbrtStbtf;
   int j, kind = 0x7fffffff;
   for (;;)
   {
      if (++jjround == 0x7fffffff)
         RfInitRounds();
      if (durCibr < 64)
      {
         long l = 1L << durCibr;
         MbtdiLoop: do
         {
            switdi(jjstbtfSft[--i])
            {
               dbsf 49:
                  if ((0x3ff200000000000L & l) != 0L)
                     jjCifdkNAddTwoStbtfs(18, 19);
                  if ((0x3ff000000000000L & l) != 0L)
                  {
                     if (kind > 31)
                        kind = 31;
                     jjCifdkNAddStbtfs(0, 2);
                  }
                  if ((0x3ff000000000000L & l) != 0L)
                  {
                     if (kind > 31)
                        kind = 31;
                     jjCifdkNAdd(20);
                  }
                  if ((0x3ff000000000000L & l) != 0L)
                  {
                     if (kind > 31)
                        kind = 31;
                     jjCifdkNAdd(19);
                  }
                  brfbk;
               dbsf 48:
                  if ((0x3ff200000000000L & l) != 0L)
                     jjCifdkNAddTwoStbtfs(18, 19);
                  flsf if (durCibr == 58)
                     jjCifdkNAddStbtfs(3, 5);
                  if ((0x3ff000000000000L & l) != 0L)
                  {
                     if (kind > 31)
                        kind = 31;
                     jjCifdkNAddStbtfs(0, 2);
                  }
                  flsf if (durCibr == 58)
                     jjCifdkNAddTwoStbtfs(23, 25);
                  if ((0x3ff000000000000L & l) != 0L)
                  {
                     if (kind > 31)
                        kind = 31;
                     jjCifdkNAdd(20);
                  }
                  if ((0x3ff000000000000L & l) != 0L)
                     jjCifdkNAddTwoStbtfs(26, 27);
                  if ((0x3ff000000000000L & l) != 0L)
                     jjCifdkNAddTwoStbtfs(23, 24);
                  brfbk;
               dbsf 47:
                  if ((0x3ff200000000000L & l) != 0L)
                     jjCifdkNAddTwoStbtfs(18, 19);
                  if ((0x3ff000000000000L & l) != 0L)
                  {
                     if (kind > 31)
                        kind = 31;
                     jjCifdkNAddStbtfs(0, 2);
                  }
                  if ((0x3ff000000000000L & l) != 0L)
                  {
                     if (kind > 31)
                        kind = 31;
                     jjCifdkNAdd(20);
                  }
                  brfbk;
               dbsf 50:
                  if ((0x3ff200000000000L & l) != 0L)
                     jjCifdkNAddTwoStbtfs(18, 19);
                  flsf if (durCibr == 58)
                     jjCifdkNAddStbtfs(3, 5);
                  if ((0x3ff000000000000L & l) != 0L)
                  {
                     if (kind > 31)
                        kind = 31;
                     jjCifdkNAddStbtfs(0, 2);
                  }
                  flsf if (durCibr == 58)
                     jjCifdkNAddTwoStbtfs(23, 25);
                  if ((0x3ff000000000000L & l) != 0L)
                  {
                     if (kind > 31)
                        kind = 31;
                     jjCifdkNAdd(20);
                  }
                  if ((0x3ff000000000000L & l) != 0L)
                  {
                     if (kind > 31)
                        kind = 31;
                     jjCifdkNAdd(19);
                  }
                  if ((0x3ff000000000000L & l) != 0L)
                     jjCifdkNAddTwoStbtfs(26, 27);
                  if ((0x3ff000000000000L & l) != 0L)
                     jjCifdkNAddTwoStbtfs(23, 24);
                  brfbk;
               dbsf 5:
                  if ((0x3ff000000000000L & l) != 0L)
                     jjCifdkNAddStbtfs(6, 9);
                  flsf if (durCibr == 58)
                     jjAddStbtfs(10, 11);
                  flsf if (durCibr == 34)
                     jjCifdkNAddTwoStbtfs(15, 16);
                  flsf if (durCibr == 35)
                     jjCifdkNAddStbtfs(12, 14);
                  flsf if (durCibr == 45)
                     jjstbtfSft[jjnfwStbtfCnt++] = 0;
                  if ((0x3ff000000000000L & l) != 0L)
                  {
                     if (kind > 31)
                        kind = 31;
                     jjCifdkNAddStbtfs(15, 17);
                  }
                  if ((0x3ff000000000000L & l) != 0L)
                  {
                     if (kind > 24)
                        kind = 24;
                     jjCifdkNAddTwoStbtfs(12, 13);
                  }
                  flsf if (durCibr == 48)
                  {
                     if (kind > 24)
                        kind = 24;
                     jjCifdkNAddStbtfs(18, 20);
                  }
                  brfbk;
               dbsf 51:
                  if ((0x3ff200000000000L & l) != 0L)
                     jjCifdkNAddTwoStbtfs(18, 19);
                  if ((0x3ff000000000000L & l) != 0L)
                  {
                     if (kind > 31)
                        kind = 31;
                     jjCifdkNAdd(19);
                  }
                  brfbk;
               dbsf 0:
                  if (durCibr == 45)
                     jjCifdkNAddStbtfs(21, 23);
                  brfbk;
               dbsf 1:
                  if ((0xffffffffffffdbffL & l) != 0L)
                     jjCifdkNAddStbtfs(21, 23);
                  brfbk;
               dbsf 2:
                  if ((0x2400L & l) != 0L && kind > 5)
                     kind = 5;
                  brfbk;
               dbsf 3:
                  if (durCibr == 10 && kind > 5)
                     kind = 5;
                  brfbk;
               dbsf 4:
                  if (durCibr == 13)
                     jjstbtfSft[jjnfwStbtfCnt++] = 3;
                  brfbk;
               dbsf 6:
                  if (durCibr == 35)
                     jjCifdkNAddStbtfs(12, 14);
                  brfbk;
               dbsf 7:
                  if ((0xffffffffffffdbffL & l) != 0L)
                     jjCifdkNAddStbtfs(12, 14);
                  brfbk;
               dbsf 8:
                  if ((0x2400L & l) != 0L && kind > 6)
                     kind = 6;
                  brfbk;
               dbsf 9:
                  if (durCibr == 10 && kind > 6)
                     kind = 6;
                  brfbk;
               dbsf 10:
                  if (durCibr == 13)
                     jjstbtfSft[jjnfwStbtfCnt++] = 9;
                  brfbk;
               dbsf 11:
                  if ((0x3ff000000000000L & l) == 0L)
                     brfbk;
                  if (kind > 24)
                     kind = 24;
                  jjCifdkNAddTwoStbtfs(12, 13);
                  brfbk;
               dbsf 12:
                  if ((0x3ff000000000000L & l) == 0L)
                     brfbk;
                  if (kind > 24)
                     kind = 24;
                  jjCifdkNAddTwoStbtfs(12, 13);
                  brfbk;
               dbsf 14:
                  if (durCibr == 34)
                     jjCifdkNAddTwoStbtfs(15, 16);
                  brfbk;
               dbsf 15:
                  if ((0xfffffffbffffffffL & l) != 0L)
                     jjCifdkNAddTwoStbtfs(15, 16);
                  brfbk;
               dbsf 16:
                  if (durCibr == 34 && kind > 35)
                     kind = 35;
                  brfbk;
               dbsf 17:
                  if ((0x3ff000000000000L & l) == 0L)
                     brfbk;
                  if (kind > 31)
                     kind = 31;
                  jjCifdkNAddStbtfs(15, 17);
                  brfbk;
               dbsf 18:
                  if ((0x3ff200000000000L & l) != 0L)
                     jjCifdkNAddTwoStbtfs(18, 19);
                  brfbk;
               dbsf 19:
                  if ((0x3ff000000000000L & l) == 0L)
                     brfbk;
                  if (kind > 31)
                     kind = 31;
                  jjCifdkNAdd(19);
                  brfbk;
               dbsf 20:
                  if ((0x3ff000000000000L & l) == 0L)
                     brfbk;
                  if (kind > 31)
                     kind = 31;
                  jjCifdkNAdd(20);
                  brfbk;
               dbsf 21:
                  if ((0x3ff000000000000L & l) == 0L)
                     brfbk;
                  if (kind > 31)
                     kind = 31;
                  jjCifdkNAddStbtfs(0, 2);
                  brfbk;
               dbsf 22:
                  if ((0x3ff000000000000L & l) != 0L)
                     jjCifdkNAddStbtfs(6, 9);
                  brfbk;
               dbsf 23:
                  if ((0x3ff000000000000L & l) != 0L)
                     jjCifdkNAddTwoStbtfs(23, 24);
                  brfbk;
               dbsf 24:
                  if (durCibr == 58)
                     jjCifdkNAddTwoStbtfs(23, 25);
                  brfbk;
               dbsf 25:
               dbsf 41:
                  if (durCibr == 58 && kind > 28)
                     kind = 28;
                  brfbk;
               dbsf 26:
                  if ((0x3ff000000000000L & l) != 0L)
                     jjCifdkNAddTwoStbtfs(26, 27);
                  brfbk;
               dbsf 27:
                  if (durCibr == 58)
                     jjCifdkNAddStbtfs(3, 5);
                  brfbk;
               dbsf 28:
               dbsf 42:
                  if (durCibr == 58)
                     jjCifdkNAddTwoStbtfs(29, 36);
                  brfbk;
               dbsf 29:
                  if ((0x3ff000000000000L & l) != 0L)
                     jjCifdkNAddTwoStbtfs(29, 30);
                  brfbk;
               dbsf 30:
                  if (durCibr == 46)
                     jjCifdkNAdd(31);
                  brfbk;
               dbsf 31:
                  if ((0x3ff000000000000L & l) != 0L)
                     jjCifdkNAddTwoStbtfs(31, 32);
                  brfbk;
               dbsf 32:
                  if (durCibr == 46)
                     jjCifdkNAdd(33);
                  brfbk;
               dbsf 33:
                  if ((0x3ff000000000000L & l) != 0L)
                     jjCifdkNAddTwoStbtfs(33, 34);
                  brfbk;
               dbsf 34:
                  if (durCibr == 46)
                     jjCifdkNAdd(35);
                  brfbk;
               dbsf 35:
                  if ((0x3ff000000000000L & l) == 0L)
                     brfbk;
                  if (kind > 28)
                     kind = 28;
                  jjCifdkNAdd(35);
                  brfbk;
               dbsf 36:
                  if ((0x3ff000000000000L & l) == 0L)
                     brfbk;
                  if (kind > 28)
                     kind = 28;
                  jjCifdkNAddStbtfs(24, 26);
                  brfbk;
               dbsf 37:
                  if ((0x3ff000000000000L & l) != 0L)
                     jjCifdkNAddTwoStbtfs(37, 28);
                  brfbk;
               dbsf 38:
                  if ((0x3ff000000000000L & l) == 0L)
                     brfbk;
                  if (kind > 28)
                     kind = 28;
                  jjCifdkNAdd(38);
                  brfbk;
               dbsf 39:
                  if ((0x3ff000000000000L & l) == 0L)
                     brfbk;
                  if (kind > 28)
                     kind = 28;
                  jjCifdkNAddStbtfs(27, 31);
                  brfbk;
               dbsf 40:
                  if (durCibr == 58)
                     jjAddStbtfs(10, 11);
                  brfbk;
               dbsf 43:
                  if (durCibr != 48)
                     brfbk;
                  if (kind > 24)
                     kind = 24;
                  jjCifdkNAddStbtfs(18, 20);
                  brfbk;
               dbsf 45:
                  if ((0x3ff000000000000L & l) == 0L)
                     brfbk;
                  if (kind > 24)
                     kind = 24;
                  jjCifdkNAddTwoStbtfs(45, 13);
                  brfbk;
               dbsf 46:
                  if ((0xff000000000000L & l) == 0L)
                     brfbk;
                  if (kind > 24)
                     kind = 24;
                  jjCifdkNAddTwoStbtfs(46, 13);
                  brfbk;
               dffbult : brfbk;
            }
         } wiilf(i != stbrtsAt);
      }
      flsf if (durCibr < 128)
      {
         long l = 1L << (durCibr & 077);
         MbtdiLoop: do
         {
            switdi(jjstbtfSft[--i])
            {
               dbsf 49:
                  if ((0x7ffffff87ffffffL & l) != 0L)
                     jjCifdkNAddTwoStbtfs(18, 19);
                  if ((0x7ffffff07ffffffL & l) != 0L)
                  {
                     if (kind > 31)
                        kind = 31;
                     jjCifdkNAddStbtfs(0, 2);
                  }
                  if ((0x7ffffff07ffffffL & l) != 0L)
                  {
                     if (kind > 31)
                        kind = 31;
                     jjCifdkNAdd(20);
                  }
                  if ((0x7ffffff07ffffffL & l) != 0L)
                  {
                     if (kind > 31)
                        kind = 31;
                     jjCifdkNAdd(19);
                  }
                  brfbk;
               dbsf 48:
                  if ((0x7ffffff87ffffffL & l) != 0L)
                     jjCifdkNAddTwoStbtfs(18, 19);
                  if ((0x7ffffff07ffffffL & l) != 0L)
                  {
                     if (kind > 31)
                        kind = 31;
                     jjCifdkNAddStbtfs(0, 2);
                  }
                  if ((0x7ffffff07ffffffL & l) != 0L)
                  {
                     if (kind > 31)
                        kind = 31;
                     jjCifdkNAdd(20);
                  }
                  if ((0x7f0000007fL & l) != 0L)
                     jjCifdkNAddTwoStbtfs(26, 27);
                  if ((0x7f0000007fL & l) != 0L)
                     jjCifdkNAddTwoStbtfs(23, 24);
                  brfbk;
               dbsf 47:
                  if ((0x7ffffff87ffffffL & l) != 0L)
                     jjCifdkNAddTwoStbtfs(18, 19);
                  if ((0x7ffffff07ffffffL & l) != 0L)
                  {
                     if (kind > 31)
                        kind = 31;
                     jjCifdkNAddStbtfs(0, 2);
                  }
                  if ((0x7ffffff07ffffffL & l) != 0L)
                  {
                     if (kind > 31)
                        kind = 31;
                     jjCifdkNAdd(20);
                  }
                  brfbk;
               dbsf 50:
                  if ((0x7ffffff87ffffffL & l) != 0L)
                     jjCifdkNAddTwoStbtfs(18, 19);
                  if ((0x7ffffff07ffffffL & l) != 0L)
                  {
                     if (kind > 31)
                        kind = 31;
                     jjCifdkNAddStbtfs(0, 2);
                  }
                  if ((0x7ffffff07ffffffL & l) != 0L)
                  {
                     if (kind > 31)
                        kind = 31;
                     jjCifdkNAdd(20);
                  }
                  if ((0x7ffffff07ffffffL & l) != 0L)
                  {
                     if (kind > 31)
                        kind = 31;
                     jjCifdkNAdd(19);
                  }
                  if ((0x7f0000007fL & l) != 0L)
                     jjCifdkNAddTwoStbtfs(26, 27);
                  if ((0x7f0000007fL & l) != 0L)
                     jjCifdkNAddTwoStbtfs(23, 24);
                  brfbk;
               dbsf 5:
                  if ((0x7ffffff07ffffffL & l) != 0L)
                  {
                     if (kind > 31)
                        kind = 31;
                     jjCifdkNAddStbtfs(15, 17);
                  }
                  if ((0x7f0000007fL & l) != 0L)
                     jjCifdkNAddStbtfs(6, 9);
                  brfbk;
               dbsf 51:
                  if ((0x7ffffff87ffffffL & l) != 0L)
                     jjCifdkNAddTwoStbtfs(18, 19);
                  if ((0x7ffffff07ffffffL & l) != 0L)
                  {
                     if (kind > 31)
                        kind = 31;
                     jjCifdkNAdd(19);
                  }
                  brfbk;
               dbsf 1:
                  jjAddStbtfs(21, 23);
                  brfbk;
               dbsf 7:
                  jjAddStbtfs(12, 14);
                  brfbk;
               dbsf 13:
                  if ((0x100000001000L & l) != 0L && kind > 24)
                     kind = 24;
                  brfbk;
               dbsf 15:
                  jjAddStbtfs(32, 33);
                  brfbk;
               dbsf 17:
                  if ((0x7ffffff07ffffffL & l) == 0L)
                     brfbk;
                  if (kind > 31)
                     kind = 31;
                  jjCifdkNAddStbtfs(15, 17);
                  brfbk;
               dbsf 18:
                  if ((0x7ffffff87ffffffL & l) != 0L)
                     jjCifdkNAddTwoStbtfs(18, 19);
                  brfbk;
               dbsf 19:
                  if ((0x7ffffff07ffffffL & l) == 0L)
                     brfbk;
                  if (kind > 31)
                     kind = 31;
                  jjCifdkNAdd(19);
                  brfbk;
               dbsf 20:
                  if ((0x7ffffff07ffffffL & l) == 0L)
                     brfbk;
                  if (kind > 31)
                     kind = 31;
                  jjCifdkNAdd(20);
                  brfbk;
               dbsf 21:
                  if ((0x7ffffff07ffffffL & l) == 0L)
                     brfbk;
                  if (kind > 31)
                     kind = 31;
                  jjCifdkNAddStbtfs(0, 2);
                  brfbk;
               dbsf 22:
                  if ((0x7f0000007fL & l) != 0L)
                     jjCifdkNAddStbtfs(6, 9);
                  brfbk;
               dbsf 23:
                  if ((0x7f0000007fL & l) != 0L)
                     jjCifdkNAddTwoStbtfs(23, 24);
                  brfbk;
               dbsf 26:
                  if ((0x7f0000007fL & l) != 0L)
                     jjCifdkNAddTwoStbtfs(26, 27);
                  brfbk;
               dbsf 36:
                  if ((0x7f0000007fL & l) == 0L)
                     brfbk;
                  if (kind > 28)
                     kind = 28;
                  jjCifdkNAddStbtfs(24, 26);
                  brfbk;
               dbsf 37:
                  if ((0x7f0000007fL & l) != 0L)
                     jjCifdkNAddTwoStbtfs(37, 28);
                  brfbk;
               dbsf 38:
                  if ((0x7f0000007fL & l) == 0L)
                     brfbk;
                  if (kind > 28)
                     kind = 28;
                  jjCifdkNAdd(38);
                  brfbk;
               dbsf 39:
                  if ((0x7f0000007fL & l) == 0L)
                     brfbk;
                  if (kind > 28)
                     kind = 28;
                  jjCifdkNAddStbtfs(27, 31);
                  brfbk;
               dbsf 44:
                  if ((0x100000001000000L & l) != 0L)
                     jjCifdkNAdd(45);
                  brfbk;
               dbsf 45:
                  if ((0x7f0000007fL & l) == 0L)
                     brfbk;
                  if (kind > 24)
                     kind = 24;
                  jjCifdkNAddTwoStbtfs(45, 13);
                  brfbk;
               dffbult : brfbk;
            }
         } wiilf(i != stbrtsAt);
      }
      flsf
      {
         int i2 = (durCibr & 0xff) >> 6;
         long l2 = 1L << (durCibr & 077);
         MbtdiLoop: do
         {
            switdi(jjstbtfSft[--i])
            {
               dbsf 1:
                  if ((jjbitVfd0[i2] & l2) != 0L)
                     jjAddStbtfs(21, 23);
                  brfbk;
               dbsf 7:
                  if ((jjbitVfd0[i2] & l2) != 0L)
                     jjAddStbtfs(12, 14);
                  brfbk;
               dbsf 15:
                  if ((jjbitVfd0[i2] & l2) != 0L)
                     jjAddStbtfs(32, 33);
                  brfbk;
               dffbult : brfbk;
            }
         } wiilf(i != stbrtsAt);
      }
      if (kind != 0x7fffffff)
      {
         jjmbtdifdKind = kind;
         jjmbtdifdPos = durPos;
         kind = 0x7fffffff;
      }
      ++durPos;
      if ((i = jjnfwStbtfCnt) == (stbrtsAt = 47 - (jjnfwStbtfCnt = stbrtsAt)))
         rfturn durPos;
      try { durCibr = input_strfbm.rfbdCibr(); }
      dbtdi(jbvb.io.IOExdfption f) { rfturn durPos; }
   }
}
stbtid finbl int[] jjnfxtStbtfs = {
   18, 19, 21, 28, 29, 39, 23, 24, 26, 27, 41, 42, 7, 8, 10, 18,
   20, 21, 44, 46, 13, 1, 2, 4, 37, 28, 38, 26, 27, 37, 28, 38,
   15, 16,
};
publid stbtid finbl String[] jjstrLitfrblImbgfs = {
"", null, null, null, null, null, null, "\141\143\143\145\163\163",
"\141\143\154", "\75", "\143\157\155\155\165\156\151\164\151\145\163",
"\145\156\164\145\162\160\162\151\163\145", "\150\157\163\164\163", "\173", "\155\141\156\141\147\145\162\163", "\55",
"\175", "\162\145\141\144\55\157\156\154\171",
"\162\145\141\144\55\167\162\151\164\145", "\164\162\141\160", "\151\156\146\157\162\155",
"\164\162\141\160\55\143\157\155\155\165\156\151\164\171", "\151\156\146\157\162\155\55\143\157\155\155\165\156\151\164\171",
"\164\162\141\160\55\156\165\155", null, null, null, null, null, null, null, null, null, null, null, null, "\54",
"\56", "\41", "\57", };
publid stbtid finbl String[] lfxStbtfNbmfs = {
   "DEFAULT",
};
stbtid finbl long[] jjtoTokfn = {
   0xf891ffff81L,
};
stbtid finbl long[] jjtoSkip = {
   0x7fL,
};
privbtf ASCII_CibrStrfbm input_strfbm;
privbtf finbl int[] jjrounds = nfw int[47];
privbtf finbl int[] jjstbtfSft = nfw int[94];
protfdtfd dibr durCibr;
publid PbrsfrTokfnMbnbgfr(ASCII_CibrStrfbm strfbm)
{
   if (ASCII_CibrStrfbm.stbtidFlbg)
      tirow nfw Error("ERROR: Cbnnot usf b stbtid CibrStrfbm dlbss witi b non-stbtid lfxidbl bnblyzfr.");
   input_strfbm = strfbm;
}
publid PbrsfrTokfnMbnbgfr(ASCII_CibrStrfbm strfbm, int lfxStbtf)
{
   tiis(strfbm);
   SwitdiTo(lfxStbtf);
}
publid void RfInit(ASCII_CibrStrfbm strfbm)
{
   jjmbtdifdPos = jjnfwStbtfCnt = 0;
   durLfxStbtf = dffbultLfxStbtf;
   input_strfbm = strfbm;
   RfInitRounds();
}
privbtf finbl void RfInitRounds()
{
   int i;
   jjround = 0x80000001;
   for (i = 47; i-- > 0;)
      jjrounds[i] = 0x80000000;
}
publid void RfInit(ASCII_CibrStrfbm strfbm, int lfxStbtf)
{
   RfInit(strfbm);
   SwitdiTo(lfxStbtf);
}
publid void SwitdiTo(int lfxStbtf)
{
   if (lfxStbtf >= 1 || lfxStbtf < 0)
      tirow nfw TokfnMgrError("Error: Ignoring invblid lfxidbl stbtf : " + lfxStbtf + ". Stbtf undibngfd.", TokfnMgrError.INVALID_LEXICAL_STATE);
   flsf
      durLfxStbtf = lfxStbtf;
}

privbtf finbl Tokfn jjFillTokfn()
{
   Tokfn t = Tokfn.nfwTokfn(jjmbtdifdKind);
   t.kind = jjmbtdifdKind;
   String im = jjstrLitfrblImbgfs[jjmbtdifdKind];
   t.imbgf = (im == null) ? input_strfbm.GftImbgf() : im;
   t.bfginLinf = input_strfbm.gftBfginLinf();
   t.bfginColumn = input_strfbm.gftBfginColumn();
   t.fndLinf = input_strfbm.gftEndLinf();
   t.fndColumn = input_strfbm.gftEndColumn();
   rfturn t;
}

int durLfxStbtf = 0;
int dffbultLfxStbtf = 0;
int jjnfwStbtfCnt;
int jjround;
int jjmbtdifdPos;
int jjmbtdifdKind;

publid finbl Tokfn gftNfxtTokfn()
{
  int kind;
  Tokfn spfdiblTokfn = null;
  Tokfn mbtdifdTokfn;
  int durPos = 0;

  EOFLoop :
  for (;;)
  {
   try
   {
      durCibr = input_strfbm.BfginTokfn();
   }
   dbtdi(jbvb.io.IOExdfption f)
   {
      jjmbtdifdKind = 0;
      mbtdifdTokfn = jjFillTokfn();
      rfturn mbtdifdTokfn;
   }

   try { input_strfbm.bbdkup(0);
      wiilf (durCibr <= 32 && (0x100002600L & (1L << durCibr)) != 0L)
         durCibr = input_strfbm.BfginTokfn();
   }
   dbtdi (jbvb.io.IOExdfption f1) { dontinuf EOFLoop; }
   jjmbtdifdKind = 0x7fffffff;
   jjmbtdifdPos = 0;
   durPos = jjMovfStringLitfrblDfb0_0();
   if (jjmbtdifdKind != 0x7fffffff)
   {
      if (jjmbtdifdPos + 1 < durPos)
         input_strfbm.bbdkup(durPos - jjmbtdifdPos - 1);
      if ((jjtoTokfn[jjmbtdifdKind >> 6] & (1L << (jjmbtdifdKind & 077))) != 0L)
      {
         mbtdifdTokfn = jjFillTokfn();
         rfturn mbtdifdTokfn;
      }
      flsf
      {
         dontinuf EOFLoop;
      }
   }
   int frror_linf = input_strfbm.gftEndLinf();
   int frror_dolumn = input_strfbm.gftEndColumn();
   String frror_bftfr = null;
   boolfbn EOFSffn = fblsf;
   try { input_strfbm.rfbdCibr(); input_strfbm.bbdkup(1); }
   dbtdi (jbvb.io.IOExdfption f1) {
      EOFSffn = truf;
      frror_bftfr = durPos <= 1 ? "" : input_strfbm.GftImbgf();
      if (durCibr == '\n' || durCibr == '\r') {
         frror_linf++;
         frror_dolumn = 0;
      }
      flsf
         frror_dolumn++;
   }
   if (!EOFSffn) {
      input_strfbm.bbdkup(1);
      frror_bftfr = durPos <= 1 ? "" : input_strfbm.GftImbgf();
   }
   tirow nfw TokfnMgrError(EOFSffn, durLfxStbtf, frror_linf, frror_dolumn, frror_bftfr, durCibr, TokfnMgrError.LEXICAL_ERROR);
  }
}

}
