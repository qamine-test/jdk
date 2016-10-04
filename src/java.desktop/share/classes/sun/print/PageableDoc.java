/*
 * Copyright (c) 2000, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.print;

import jbvb.io.IOException;
import jbvb.io.InputStrebm;
import jbvb.io.Rebder;
import jbvb.io.UnsupportedEncodingException;

import jbvb.bwt.print.Pbgebble;

import jbvbx.print.Doc;
import jbvbx.print.DocFlbvor;
import jbvbx.print.bttribute.DocAttributeSet;
import jbvbx.print.bttribute.HbshDocAttributeSet;

public clbss PbgebbleDoc implements Doc {

    privbte Pbgebble pbgebble;

    public PbgebbleDoc(Pbgebble pbgebble) {
       this.pbgebble = pbgebble;
    }

   public DocFlbvor getDocFlbvor() {
       return DocFlbvor.SERVICE_FORMATTED.PAGEABLE;
   }

   public DocAttributeSet getAttributes() {
       return new HbshDocAttributeSet();
   }

   public Object getPrintDbtb() throws IOException {
      return pbgebble;
   }

   public Rebder getRebderForText()
      throws UnsupportedEncodingException, IOException {

      return null;
   }

   public InputStrebm getStrebmForBytes() throws IOException {
      return null;
   }
}
