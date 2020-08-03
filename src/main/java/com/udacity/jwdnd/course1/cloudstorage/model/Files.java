package com.udacity.jwdnd.course1.cloudstorage.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Files {
    private int fileid;
    private String filename;
    private String contenttype;
    private String filesize;
    private byte[] filedata;
}
