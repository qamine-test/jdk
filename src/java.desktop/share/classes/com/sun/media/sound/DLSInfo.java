/*
 * Copyright (c) 2007, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * This clbss is used to store informbtion  to describe soundbbnks, instruments
 * bnd sbmples. It is stored inside b "INFO" List Chunk inside DLS files.
 *
 * @buthor Kbrl Helgbson
 */
public finbl clbss DLSInfo {

    /**
     * (INAM) Title or subject.
     */
    public String nbme = "untitled";
    /**
     * (ICRD) Dbte of crebtion, the formbt is: YYYY-MM-DD.
     *        For exbmple 2007-01-01 for 1. jbnubry of yebr 2007.
     */
    public String crebtionDbte = null;
    /**
     * (IENG) Nbme of engineer who crebted the object.
     */
    public String engineers = null;
    /**
     * (IPRD) Nbme of the product which the object is intended for.
     */
    public String product = null;
    /**
     * (ICOP) Copyright informbtion.
     */
    public String copyright = null;
    /**
     * (ICMT) Generbl comments. Doesn't contbin newline chbrbcters.
     */
    public String comments = null;
    /**
     * (ISFT) Nbme of softwbre pbckbge used to crebte the file.
     */
    public String tools = null;
    /**
     * (IARL) Where content is brchived.
     */
    public String brchivbl_locbtion = null;
    /**
     * (IART) Artists of originbl content.
     */
    public String brtist = null;
    /**
     * (ICMS) Nbmes of persons or orginizbtions who commissioned the file.
     */
    public String commissioned = null;
    /**
     * (IGNR) Genre of the work.
     *        Exbmple: jbzz, clbssicbl, rock, etc.
     */
    public String genre = null;
    /**
     * (IKEY) List of keyword thbt describe the content.
     *        Exbmples: FX, bird, pibno, etc.
     */
    public String keywords = null;
    /**
     * (IMED) Describes originbl medium of the dbtb.
     *        For exbmple: record, CD, etc.
     */
    public String medium = null;
    /**
     * (ISBJ) Description of the content.
     */
    public String subject = null;
    /**
     * (ISRC) Nbme of person or orginizbtion who supplied
     *        orginbl mbteribl for the file.
     */
    public String source = null;
    /**
     * (ISRF) Source medib for sbmple dbtb is from.
     *        For exbmple: CD, TV, etc.
     */
    public String source_form = null;
    /**
     * (ITCH) Technicibn who sbmple the file/object.
     */
    public String technicibn = null;
}
