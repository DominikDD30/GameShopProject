import { Flex,Text,Icon, useMediaQuery } from '@chakra-ui/react'
import { IoMdDownload } from "react-icons/io";
import { MdDownloadDone } from "react-icons/md";
import React, { useState } from 'react'
import html2canvas from 'html2canvas';
import jsPDF from 'jspdf';

const DownloadRaportButton = () => {
    const [saved,setSaved]=useState(false);

    const handleClick = () => {
        setSaved(true);
        const input = document.getElementById("Report");
        html2canvas(input!, { logging: true, useCORS: true }).then(canvas => {
            canvas.toBlob(blob => {
                const link = document.createElement('a');
                link.href = URL.createObjectURL(blob!);
                link.download = 'report.png';
                link.click();
                URL.revokeObjectURL(link.href);
            }, 'image/png');
        })
    }
 
  return (
    <Flex  ml={10}  boxShadow='md'  width='140px'
     justifyContent='space-between' alignItems='center' p='10px' height='40px' borderRadius='5px'
      cursor='pointer' onClick={handleClick}>
       <Text>download</Text>
     <Icon as={saved?MdDownloadDone:IoMdDownload}/>
   </Flex>
  )
}

//pdf Option

  //  const handleClick=()=>{
  //       setSaved(true);
  //       const input=document.getElementById("Raport");
  //       html2canvas(input!,{logging:true,useCORS:true,scale:2}).then(canvas=>{
  //           const imgWidth=210;
  //           const imgHeight=canvas.height*imgWidth/canvas.width;
  //           const imgData=canvas.toDataURL("image/png",1.0);
  //           const pdf=new jsPDF('p','mm','a4');
  //           pdf.addImage(imgData,'PNG',0,0,imgWidth,imgHeight);
  //           pdf.save('raport.pdf');
  //       })
  //   }


export default DownloadRaportButton