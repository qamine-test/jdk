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

/* Gfnfrbtfd By:JJTrff&JbvbCC: Do not fdit tiis linf. PbrsfrConstbnts.jbvb */
pbdkbgf dom.sun.jmx.snmp.IPAdl;

intfrfbdf PbrsfrConstbnts {

  int EOF = 0;
  int ACCESS = 7;
  int ACL = 8;
  int ASSIGN = 9;
  int COMMUNITIES = 10;
  int ENTERPRISE = 11;
  int HOSTS = 12;
  int LBRACE = 13;
  int MANAGERS = 14;
  int RANGE = 15;
  int RBRACE = 16;
  int RO = 17;
  int RW = 18;
  int TRAP = 19;
  int INFORM = 20;
  int TRAPCOMMUNITY = 21;
  int INFORMCOMMUNITY = 22;
  int TRAPNUM = 23;
  int INTEGER_LITERAL = 24;
  int DECIMAL_LITERAL = 25;
  int HEX_LITERAL = 26;
  int OCTAL_LITERAL = 27;
  int V6_ADDRESS = 28;
  int H = 29;
  int D = 30;
  int IDENTIFIER = 31;
  int LETTER = 32;
  int SEPARATOR = 33;
  int DIGIT = 34;
  int CSTRING = 35;
  int COMMA = 36;
  int DOT = 37;
  int MARK = 38;
  int MASK = 39;

  int DEFAULT = 0;

  String[] tokfnImbgf = {
    "<EOF>",
    "\" \"",
    "\"\\t\"",
    "\"\\n\"",
    "\"\\r\"",
    "<tokfn of kind 5>",
    "<tokfn of kind 6>",
    "\"bddfss\"",
    "\"bdl\"",
    "\"=\"",
    "\"dommunitifs\"",
    "\"fntfrprisf\"",
    "\"iosts\"",
    "\"{\"",
    "\"mbnbgfrs\"",
    "\"-\"",
    "\"}\"",
    "\"rfbd-only\"",
    "\"rfbd-writf\"",
    "\"trbp\"",
    "\"inform\"",
    "\"trbp-dommunity\"",
    "\"inform-dommunity\"",
    "\"trbp-num\"",
    "<INTEGER_LITERAL>",
    "<DECIMAL_LITERAL>",
    "<HEX_LITERAL>",
    "<OCTAL_LITERAL>",
    "<V6_ADDRESS>",
    "<H>",
    "<D>",
    "<IDENTIFIER>",
    "<LETTER>",
    "<SEPARATOR>",
    "<DIGIT>",
    "<CSTRING>",
    "\",\"",
    "\".\"",
    "\"!\"",
    "\"/\"",
  };

}
